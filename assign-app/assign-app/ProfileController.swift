//
//  ProfileController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 29/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class ProfileController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var segmentedControl: UISegmentedControl!
    
    // The API service
    var apiService: ApiService?
    
    var size = 21
    var assignmentsStart = 1
    var isLoading = false
    
    // The provided data from the segue 
    var currentUser: User?
    
    var overviewArray: [Post] = []
    var assignmentsArray: [Post] = []
    var activityArray: [Post] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Init API service
        apiService = ApiService()
        
        // Retrieve User
        currentUser = Storage.getUser()
        
        // TODO set multiple fields
        self.nameLabel.text = currentUser?.name
        
        // Set delegates
        tableView.delegate = self
        tableView.dataSource = self
        
        // TODO make API call to fill the assignments table
        self.apiService?.getPostsByUser(size: 21, start: 1, id: currentUser!.id!) { posts in
            
            self.assignmentsArray = posts!
            self.tableView.reloadData()
        }
        
        // Set the layout of the view
        // Get rid of the nasty nav border
        // TODO add to storyboard
        view.backgroundColor = UIColor(red: 0.98, green: 0.98, blue: 0.98, alpha: 1)
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")
    }
    
    /// Set StatusBartStyle to .lightContent
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        UIApplication.shared.statusBarStyle = .lightContent
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        var returnValue = 0
        
        switch(segmentedControl.selectedSegmentIndex)
        {
        case 0:
            returnValue = overviewArray.count
            break
        case 1:
            returnValue = activityArray.count
            break
        case 2:
            returnValue = assignmentsArray.count
            break
            
        default:
            break
            
        }
        
        return returnValue
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ProfileCell", for: indexPath as IndexPath)
        
        switch(segmentedControl.selectedSegmentIndex)
        {
        case 0:
            let post = overviewArray[indexPath.row] as Post
            
            if let nameLabel = cell.viewWithTag(501) as? UILabel {
            nameLabel.text = post.title
            }
            break
        case 1:
            let post = activityArray[indexPath.row] as Post
            
            if let nameLabel = cell.viewWithTag(501) as? UILabel {
                nameLabel.text = post.title
            }
            break
            
        case 2:
            let post = assignmentsArray[indexPath.row] as Post
            
            if let nameLabel = cell.viewWithTag(501) as? UILabel {
                nameLabel.text = post.title
            }
            break
            
        default:
            break
            
        }
        
        return cell
    }
    
    /// When changed to another tableView.
    @IBAction func segmentedControlActionChanged(_ sender: Any) {
        self.tableView.reloadData()
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
        
        // Checks if the table is currently loading
        if !isLoading {
            
            switch(segmentedControl.selectedSegmentIndex)
            {
            case 0:
                break
            case 1:

                break
                
            case 2:
                // Need atleast 20 posts to try and load the next posts
                if assignmentsArray.count >= 21 {
                    
                    // Sets variables to indicate loading
                    self.isLoading = true
                    self.tableView.tableFooterView?.isHidden = false
                    
                    // Add 20 to try and load the next posts
                    self.assignmentsStart += 20
                    apiService?.getPostsByUser(size: size, start: assignmentsStart,
                                               id: currentUser!.id!) { p in
                        // Add posts
                        self.assignmentsArray += p!
                        self.tableView.reloadData()
                                                
                        // Sets variables to indicate loading
                        self.isLoading = false
                        self.tableView.tableFooterView?.isHidden = true
                    }
                }
                break
                
            default:
                break
                
            }
        }
    }
    
}


