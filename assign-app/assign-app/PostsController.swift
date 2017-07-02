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
class PostsController: UITableViewController {
    
    // Posts array for tableview
    var posts = [Post]()
    
    // Pagination variables
    let size = 21 // Amount of Posts to load next
    var start = 0 // Starting index of the posts
    var isLoading = false // Is currently loading posts
    var reachedEnd = false // Check if there a no new posts

    // API service
    var apiService: ApiService?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Init API service
        apiService = ApiService()

        // On viewLoad get the posts from the API
        // Starting values of size is 20 and start 1
        self.apiService?.getPosts(size: 21, start: start) { posts in
            
            // TODO add Data to Core Data as cache
            // Sets the posts and refreshes the table
            self.posts = posts!
            self.tableView.reloadData()
        }
        
        // Layout settings
        let nib = UINib(nibName: "PostCellNib", bundle: nil)
        tableView.register(nib, forCellReuseIdentifier: "PostCell") // For NIB Cell
        view.backgroundColor = UIColor(red: 0.98, green: 0.98, blue: 0.98, alpha: 1)
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")

    }
    
    /// Set StatusBartStyle to default.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        UIApplication.shared.statusBarStyle = .default
    }

    // MARK: - Table view with Posts

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return posts.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
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
            profileImage.contentMode = .scaleAspectFit
            profileImage.af_setImage(withURL: url)
        }
        
        return cell
    }
    
    /// Add data to the segue before triggering.
    ///
    /// TODO Modify so it works with push and pop
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "PostDetailSegue" ,
            let nextView = segue.destination as? PostDetailController,
            let indexPath = self.tableView.indexPathForSelectedRow {
            let post = posts[indexPath.row]
            nextView.currentPost = post
        }
    }
    
    /// Pull and refesh function on the tableView.
    /// Starting values of size is 20 and start 1
    @IBAction func refreshAction(_ sender: Any) {
        self.apiService?.getPosts(size: 21, start: 1) { posts in
            
            // TODO add Data to Core Data as cache
            // Reloads the tableView and stops the refresh animation
            self.posts = posts!
            self.tableView.reloadData()
            self.refreshControl?.endRefreshing()
            
            // Reset infinite loading variables
            self.start = 0
            self.reachedEnd = false
        }
    }
    
    /// ScrollView infinite scroll.
    override func scrollViewDidScroll(_ scrollView: UIScrollView) {
        
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
