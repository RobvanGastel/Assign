//
//  ProfileController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 29/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit
import AlamofireImage

class ProfileController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var profileImage: UIImageView!
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var segmentedControl: UISegmentedControl!
    @IBOutlet weak var settingsImage: UIButton!
    
    // The API service
    var apiService: ApiService?
    
    // Pagination variables
    let size = 21 // Amount of Posts to load next
    var assignmentsStart = 0 // Starting index of the assignments
    var assignmentsReachedEnd = false // Check if there a no new posts
    var isLoading = false // Is currently loading posts
    
    // The provided data from the segue 
    var currentUser: User?
    
    // TableView arrays
    var overviewArray: [Post] = []
    var assignmentsArray: [Post] = []
    var activityArray: [Post] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Init API service
        apiService = ApiService()
        
        // Set delegates
        tableView.delegate = self
        tableView.dataSource = self
        
        self.setProfile() // Set profile values
        self.fillTables() // Set table values

        // TODO improve gesture for settings
        let tapRec = UITapGestureRecognizer()
        tapRec.addTarget(self, action: #selector(ProfileController.settingsTap))
        settingsImage.addGestureRecognizer(tapRec)
        
        // Layout settings
        view.backgroundColor = UIColor(red: 0.98, green: 0.98, blue: 0.98, alpha: 1)
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")
    }
    
    /// Set StatusBartStyle to .lightContent.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        UIApplication.shared.statusBarStyle = .lightContent
    }
    
    /// Set profile of the User.
    func setProfile() {
        // Retrieve User
        currentUser = Storage.getUser()
        
        // TODO add more fields
        self.nameLabel.text = currentUser?.name
        
        // TODO improve scaling
        let url = URL(string: (currentUser?.profileImage)!)
        let filter = AspectScaledToFillSizeFilter(size: profileImage.frame.size)
        profileImage.af_setImage(withURL: url!, filter: filter)
    }
    
    
    /// API call to fill all the profile tables.
    ///
    /// TODO Add calls for all tables
    func fillTables() {
        self.apiService?.getPostsByUser(size: size, start: assignmentsStart, id: currentUser!.id!) { posts in
            
            self.assignmentsArray = posts!
            self.tableView.reloadData()
        }
    }
    
    /// Redirect to settings view.
    func settingsTap() {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: "SettingsNavigationController")
        self.present(vc, animated: true, completion: nil)
    }
    
    
    /// When changed to another tableView.
    @IBAction func segmentedControlActionChanged(_ sender: Any) {
        self.tableView.reloadData()
    }
    
    /// ScrollView for infinite scroll.
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
            switch(segmentedControl.selectedSegmentIndex) // Check for different tables
            {
            case 0:
                break
            case 1:
                
                break
                
            case 2:
                if assignmentsArray.count >= 21
                    && assignmentsReachedEnd == false { // Need atleast 21 posts
                    
                    self.isLoading = true
                    self.tableView.tableFooterView?.isHidden = false
                    
                    // Add 20 to load the next posts
                    self.assignmentsStart += 20
                    apiService?.getPostsByUser(size: size, start: assignmentsStart,
                                               id: currentUser!.id!) { p in
                               
                        if (p?.count)! < 20 { // Check if all posts found
                            print("API: No new posts")
                            self.assignmentsReachedEnd = true
                        }
                                                
                        self.assignmentsArray += p!
                                                
                        self.tableView.reloadData()
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

    // MARK: - Table view with Posts
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        var returnValue = 0
        
        // Check for different tables
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
        
        // Check for different tables
        switch(segmentedControl.selectedSegmentIndex)
        {
        case 0:
            let post = overviewArray[indexPath.row] as Post
            
            if let titleLabel = cell.viewWithTag(401) as? UILabel {
                titleLabel.text = post.title
            }
            break
        case 1:
            let post = activityArray[indexPath.row] as Post
            
            if let titleLabel = cell.viewWithTag(401) as? UILabel {
                titleLabel.text = post.title
            }
            
            break
            
        case 2:
            let post = assignmentsArray[indexPath.row] as Post
            
            if let titleLabel = cell.viewWithTag(401) as? UILabel {
                titleLabel.text = post.title
            }
            
            if let textLabel = cell.viewWithTag(402) as? UILabel {
                textLabel.text = post.text
            }
            
            if let dateLabel = cell.viewWithTag(403) as? UILabel {
                dateLabel.text = post.dateCreated?.timeAgoSimple
            }
            break
            
        default:
            break
            
        }
        
        return cell
    }
}


