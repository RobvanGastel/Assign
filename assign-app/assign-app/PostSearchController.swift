//
//  PostSearchController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 28/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class PostSearchController: UIViewController, UITableViewDataSource, UITableViewDelegate, UISearchBarDelegate {

    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var tableView: UITableView!
    
    // Posts array for tableview
    var posts = [Post]()
    
    // API service
    var apiService: ApiService?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Hides the keyboard when tapping on the screen
        self.hideKeyboardWhenTappedAround()

        // Init API service
        apiService = ApiService()
        
        // Declare delegates
        searchBar.delegate = self
        tableView.delegate = self
        tableView.dataSource = self
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        self.apiService?.saerchPosts(query: searchBar.text!) { posts in
            
            // TODO add Data to Core Data as cache
            // Sets the posts and refreshes the table
            self.posts = posts!
            self.tableView.reloadData()
        }
        
        self.searchBar.endEditing(true)
        
    }
    
    // MARK: - Table view data source

    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
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
        
        if let profileLabel = cell.viewWithTag(204) as? UILabel {
            profileLabel.text = post.profile
        }
        
        return cell
    }
    
    /// Add data to the segue before triggering.
    ///
    /// TODO Modify so it works with push and pop
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "PostDetailSegueSearch" ,
            let nextView = segue.destination as? PostDetailController,
            let indexPath = self.tableView.indexPathForSelectedRow {
            let post = posts[indexPath.row]
            nextView.currentPost = post
        }
    }
}
