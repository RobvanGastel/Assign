//
//  OverviewTableViewController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 16/01/17.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit
import AlamofireImage

/// Controller to view all the relevant posts.
/// TODO: add Data to Core Data as cache
/// TODO: Modify so it works with push and pop
/// TODO: Set placeholder images before posts have been loaded
class PostsController: UIViewController, UITableViewDelegate, UITableViewDataSource, RefreshPostsDelegate {
    
    @IBOutlet weak var tableView: UITableView!
    
    // Posts array for tableview
    var posts = [Post]()
    
    // Pagination variables
    let size = 21 // Amount of Posts to load next
    var start = 0 // Starting index of the posts
    var isLoading = false // Is currently loading posts
    var reachedEnd = false // Check if there a no new posts
    
    lazy var refreshControl: UIRefreshControl = {
        let refreshControl = UIRefreshControl()
        refreshControl.addTarget(self, action:
            #selector(PostsController.handleRefresh(_:)),
                                 for: UIControlEvents.valueChanged)
        refreshControl.tintColor = UIColor(hexString: "#FFA92F")
        
        return refreshControl
    }()
    
    // API service
    var apiService: ApiService?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Init API service
        apiService = ApiService()

        tableView.delegate = self
        tableView.dataSource = self
        
        self.tableView.backgroundView = self.refreshControl
        
        // On viewLoad get the posts from the API
        // Starting values of size is 20 and start 1
        self.apiService?.getPosts(size: 21, start: start) { posts in
            
            // TODO add Data to Core Data as cache
            // Sets the posts and refreshes the table
            self.posts = posts!
            self.tableView.reloadData()
        }
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        // Layout settings
        UIApplication.shared.statusBarStyle = .default
        view.backgroundColor = UIColor(red: 245/255, green: 245/255, blue: 245/255, alpha: 1)
//        self.navigationController?.popViewController(animated: true)
        self.navigationController?.navigationBar.barTintColor = UIColor(red: 245/255, green: 245/255, blue: 245/255, alpha: 1)
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")
    }

    /// Add some space to the TableView
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        self.tableView.contentInset = UIEdgeInsets(top: 5, left: 0, bottom: 5, right: 0)
    }
    
    // MARK: - Table view with Posts

    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return posts.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        
        let post = posts[indexPath.row] as Post
        
        if let titleLabel = cell.viewWithTag(101) as? UILabel {
            titleLabel.text = post.title
        }
        
        if let nameLabel = cell.viewWithTag(102) as? UILabel {
            nameLabel.text = post.user?.name
        }
        
        if let dateLabel = cell.viewWithTag(103) as? UILabel {
            dateLabel.text = post.dateCreated?.timeAgoSimple
        }
        
        if let profileImage = cell.viewWithTag(104) as? UIImageView {
            let url = URL(string: (post.user?.profileImage)!)!
            let filter = AspectScaledToFillSizeFilter(size: profileImage.frame.size)
            profileImage.af_setImage(withURL: url, filter: filter)
        }
        
        return cell
    }
    
    /// Segue for Post Detail View
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let post = posts[indexPath.row]
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        
        if post.user!.id != Storage.getUser().id {

            let vc = storyboard.instantiateViewController(withIdentifier: "PostDetailController") as! PostDetailController
            vc.currentPost = post
            vc.delegate = self
            self.navigationController?.pushViewController(vc, animated: true)
        } else {
            let vc = storyboard.instantiateViewController(withIdentifier: "OwnPostDetailController") as! OwnPostDetailController
            vc.currentPost = post
            vc.delegate = self
            self.navigationController?.pushViewController(vc, animated: true)
        }
    }
    
    /// Add data to the segue before triggering.
    ///
    /// TODO: Modify so it works with push and pop
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "PostDetailSegue",
            let nextView = segue.destination as? PostDetailController,
            let indexPath = self.tableView.indexPathForSelectedRow {
            let post = posts[indexPath.row]
            nextView.currentPost = post
        }
        
        // If Add post segue add delegate
        if segue.identifier == "PostAddSegue",
            let nextView = segue.destination as? AddPostController {
                nextView.delegate = self
        }
    }
    
    /// Pull and refesh function on the tableView.
    func handleRefresh(_ refreshControl: UIRefreshControl) {
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
            self.refreshPosts()
        }
    }
    
    /// Refreshes the posts in the view.
    /// Starting values of size is 20 and start 1.
    func refreshPosts() {
        // Reset infinite loading variables
        self.start = 0
        self.reachedEnd = false

        self.apiService?.getPosts(size: 21, start: start) { posts in

            // TODO: add Data to Core Data as cache
            // Reloads the tableView and stops the refresh animation
            self.posts = posts!
            self.tableView.reloadData()
            self.refreshControl.endRefreshing()
        }
    }
    
    /// ScrollView infinite scroll.
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        
        // calculate scrollView size
        let currentOffset = scrollView.contentOffset.y
        let maximumOffset = scrollView.contentSize.height - scrollView.frame.size.height
        let deltaOffset = maximumOffset - currentOffset
        
        // If the scrollView is at the bottom load new posts
        if deltaOffset <= 0 {
            self.loadPosts()
        }
    }
    
    /// Load next posts and add to the tableView
    func loadPosts() {
        if !isLoading { // Checks if the table is currently loading
            if posts.count >= 21 && reachedEnd == false { // Need atleast 21 posts
                
                self.isLoading = true
                self.tableView.tableFooterView?.isHidden = false
                
                // Add 20 to load the next posts
                self.start += 20
                apiService?.getPosts(size: size, start: start) { p in
                    
                    if (p?.count)! < 20 { // Check if all posts found
                        print("API: No new posts")
                        self.reachedEnd = true
                    }
                    
                    self.posts += p!
                    
                    self.tableView.reloadData()
                    self.isLoading = false
                    self.tableView.tableFooterView?.isHidden = true
                }
            }
        }
    }
}
