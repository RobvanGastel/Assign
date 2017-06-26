//
//  OverviewTableViewController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 16/01/17.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class PostsController: UITableViewController {
    
    var posts = [Post]()
    var apiService: ApiService?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = UIColor(red: 0.98, green: 0.98, blue: 0.98, alpha: 1)
        
        // Get rid of the nasty nav border
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
        
        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
        
        
        // Uncomment to use default Posts
        //posts = [
        //    Post(id: 1, title: "What Makes Flyers Unrivaled", user: "Landon Gordon", dateCreated: Date(), text: "asdsadasd", profile: "profile@2x.jpg"),
        //    Post(id: 2, title: "5 Reasons To Purchase Desktop ComputersDirectory Add Url Free", user: "Stanley Henderson", dateCreated: Date(), text: "asdsadasd", profile: "https://assets.entrepreneur.com/content/16x9/822/20150406145944-dos-donts-taking-perfect-linkedin-profile-picture-selfie-mobile-camera-2.jpeg")]
        
        apiService = ApiService()
    
        self.apiService?.getPosts() { posts in
            //TODO add Data to Core Data
            for post in posts! {
                print("Post: title: \(String(describing: post.title))")
            }
            
            self.posts = posts!
            self.tableView.reloadData()
        }
        

    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
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
        
        if let titleLabel = cell.viewWithTag(101) as? UILabel {
            titleLabel.text = post.title
        }
        
        if let nameLabel = cell.viewWithTag(102) as? UILabel {
            nameLabel.text = post.user
        }
        
        if let dateLabel = cell.viewWithTag(103) as? UILabel {
            dateLabel.text = post.dateCreated?.description
        }
        
        if let profileLabel = cell.viewWithTag(104) as? UILabel {
            profileLabel.text = post.profile
        }
        
        return cell
    }
}
