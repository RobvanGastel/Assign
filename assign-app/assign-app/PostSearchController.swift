//
//  PostSearchController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 28/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit
import Foundation
import AlamofireImage

/// TODO: Modify so it works with push and pop
class PostSearchController: UIViewController, UITableViewDataSource, UITableViewDelegate, UISearchBarDelegate {

    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var tableView: UITableView!
    
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
        // Hides the keyboard when tapping on the screen
        self.hideKeyboardWhenTappedAround()

        // Init API service
        apiService = ApiService()
        
        // Initializes the delegates
        searchBar.delegate = self
        tableView.delegate = self
        tableView.dataSource = self
    }
    
    /// Set StatusBartStyle to .default and sets navigationbar.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        // Layout settings
        view.backgroundColor = UIColor(red: 245/255, green: 245/255, blue: 245/255, alpha: 1)
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")
        self.navigationController?.navigationBar.barTintColor = UIColor(red: 1, green: 1, blue: 1, alpha: 1)
    }
    
    // Add some space to the TableView
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        self.tableView.contentInset = UIEdgeInsets(top: 5, left: 0, bottom: 5, right: 0)
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        
        // Reset infinite loading variables
        self.start = 0
        self.reachedEnd = false
        
        self.apiService?.searchPosts(size: size, start: start, query: searchBar.text!) { posts in
            // Sets the posts and refreshes the table
            self.posts = posts!
            self.tableView.reloadData()
        }
        
        self.searchBar.endEditing(true)
    }
    
    
    // MARK: - Table view data source

    class TableViewHelper {
        class func EmptyStateView(message: String, viewController: PostSearchController) {
            let messageLabel = UILabel(frame: CGRect(x: 0, y: 0, width: viewController.view.bounds.size.width, height: viewController.view.bounds.size.height))
            messageLabel.text = message
            // messageLabel.textColor = UIColor.blackColor()
            messageLabel.numberOfLines = 0;
            messageLabel.textAlignment = .center;
            messageLabel.font = UIFont(name: "", size: 15)
            messageLabel.sizeToFit()
            viewController.tableView.backgroundView = messageLabel;
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        if posts.count > 0 {
            return 1
        } else {
            print("Empty state: Search")
            TableViewHelper.EmptyStateView(message: "Er is momenteel geen vraag met deze zoekterm. Probeer iets anders.", viewController: self)
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return posts.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "SearchCell", for: indexPath)
        
        let post = posts[indexPath.row] as Post
        
        if let titleLabel = cell.viewWithTag(201) as? UILabel {
            titleLabel.text = post.title
        }
        
        if let nameLabel = cell.viewWithTag(202) as? UILabel {
            nameLabel.text = post.user?.name
        }
        
        if let dateLabel = cell.viewWithTag(203) as? UILabel {
            dateLabel.text = post.dateCreated?.timeAgoSimple
        }
        
        if let profileImage = cell.viewWithTag(204) as? UIImageView {
            let url = URL(string: (post.user?.profileImage)!)!
            let filter = AspectScaledToFillSizeFilter(size: profileImage.frame.size)
            profileImage.af_setImage(withURL: url, filter: filter)
        }
        
        return cell
    }
    
    /// Add data to the segue before triggering.
    ///
    /// TODO: Modify so it works with push and pop
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "PostDetailSegueSearch" ,
            let nextView = segue.destination as? PostDetailController,
            let indexPath = self.tableView.indexPathForSelectedRow {
            let post = posts[indexPath.row]
            nextView.currentPost = post
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
                apiService?.searchPosts(size: size, start: start, query: searchBar.text!)
                { p in
                    
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
