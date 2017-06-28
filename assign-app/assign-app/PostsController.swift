//
//  OverviewTableViewController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 16/01/17.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

/// Controller to view all the relevant posts.
class PostsController: UITableViewController {

    // Posts array for tableview
    var posts = [Post]()

    // API service
    var apiService: ApiService?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()


        // Uncomment to use default Posts
        // posts = [
        //    Post(id: 1, title: "What Makes Flyers Unrivaled", user: "Landon Gordon", dateCreated: Date(), text: "asdsadasd", profile: "profile@2x.jpg"),
        //    Post(id: 2, title: "5 Reasons To Purchase Desktop ComputersDirectory Add Url Free", user: "Stanley Henderson", dateCreated: Date(), text: "asdsadasd", profile: "https://assets.entrepreneur.com/content/16x9/822/20150406145944-dos-donts-taking-perfect-linkedin-profile-picture-selfie-mobile-camera-2.jpeg")]

        // Init API service
        apiService = ApiService()

        // On viewLoad get the posts from the API
        self.apiService?.getPosts() { posts in
            
            // TODO add Data to Core Data as cache
            for post in posts! {
                print("Post: title: \(String(describing: post.title))")
            }

            // Sets the posts and refreshes the table
            self.posts = posts!
            self.tableView.reloadData()
        }
        
        // Layout settings
        // TODO place these rules in storyboard
        //
        // Set the layout of the view
        view.backgroundColor = UIColor(red: 0.98, green: 0.98, blue: 0.98, alpha: 1)

        // Get rid of the nasty nav border
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

        if let profileLabel = cell.viewWithTag(104) as? UILabel {
            profileLabel.text = post.profile
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
    @IBAction func refreshAction(_ sender: Any) {
        self.apiService?.getPosts() { posts in
            
            // TODO add Data to Core Data as cache
            for post in posts! {
                print("Post: title: \(String(describing: post.title))")
            }
            
            // Reloads the tableView and stops the refresh animation
            self.posts = posts!
            self.tableView.reloadData()
            self.refreshControl?.endRefreshing()
        }
    }
}
