//
//  PostSearchController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 28/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class PostSearchController: UITableViewController, UISearchBarDelegate {

    @IBOutlet weak var searchBar: UISearchBar!
    
    // Posts array for tableview
    var posts = [Post]()
    
    var searchActive : Bool = false
    
    // API service
    var apiService: ApiService?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Init API service
        apiService = ApiService()
        
        // Declare searchBar delegate
        searchBar.delegate = self
    }

    /// On the last return triggers the register method.
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        
        return true
    }
    
    func searchBarTextDidBeginEditing(searchBar: UISearchBar) {
        searchActive = true;
    }
    
    func searchBarTextDidEndEditing(searchBar: UISearchBar) {
        searchActive = false;
    }
    
    func searchBarCancelButtonClicked(searchBar: UISearchBar) {
        searchActive = false;
    }
    
    func searchBarSearchButtonClicked(searchBar: UISearchBar) {
        searchActive = false;
    }
    
    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return posts.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        
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
}
