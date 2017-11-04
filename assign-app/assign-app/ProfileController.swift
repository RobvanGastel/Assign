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
    @IBOutlet weak var specialisationLabel: UILabel!
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var segmentedControl: UISegmentedControl!
    @IBOutlet weak var settingsImage: UIButton!
    
    // The API service
    var apiService: ApiService?
    
    // Pagination variables
    let size = 21 // Amount of Posts to load next
    var assignmentsStart = 0 // Starting index of the assignments
    var assignmentsReachedEnd = false // Check if there are no new posts
    
    var activityStart = 0 // Starting index of the activity
    var activityReachedEnd = false // Check if there are no new replies
    
    var itemsStart = 0 // Starting index of the activity
    var itemsReachedEnd = false // Check if there are no new items
    
    var isLoading = false // Is currently loading posts
    
    // The provided data from the segue 
    var currentUser: User?
    
    // TableView arrays
    var itemsArray: [Item] = []
    var assignmentsArray: [Post] = []
    var activityArray: [Reply] = []
    
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
        view.backgroundColor = UIColor(red: 245/255, green: 245/255, blue: 245/255, alpha: 1)
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")
    }
    
    /// Set StatusBartStyle to .lightContent.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        UIApplication.shared.statusBarStyle = .lightContent
    }
    
    // Add some space to the TableView
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        self.tableView.contentInset = UIEdgeInsets(top: 5, left: 0, bottom: 5, right: 0)
    }
    
    /// Set profile of the User.
    func setProfile() {
        // Retrieve User
        currentUser = Storage.getUser()
        
        self.nameLabel.text = currentUser?.name
        self.specialisationLabel.text = currentUser?.specialisation
        
        // TODO improve scaling
        let url = URL(string: (currentUser?.profileImage)!)
        let filter = AspectScaledToFillSizeFilter(size: profileImage.frame.size)
        profileImage.af_setImage(withURL: url!, filter: filter)
        
        segmentedControl.customizeAppearance(for: 44)
    }
    
    /// API call to fill all the profile tables.
    ///
    /// TODO Add calls for all tables
    func fillTables() {
        self.apiService?.getPostsByUser(size: size, start: assignmentsStart, id: currentUser!.id!) { posts in
            self.assignmentsArray = posts!
            self.tableView.reloadData()
        }
        
        self.apiService?.getRepliesByUser(size: size, start: activityStart, id: currentUser!.id!) {
            replies in
            self.activityArray = replies!
            self.tableView.reloadData()
        }
        
        self.apiService?.getItemsByUser(size: size, start: itemsStart, id: currentUser!.id!) {
            items in
            self.itemsArray = items!
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
                if itemsArray.count >= 21
                    && itemsReachedEnd == false { // Need atleast 21 posts
                
                self.isLoading = true
                self.tableView.tableFooterView?.isHidden = false
                
                // Add 20 to load the next posts
                self.itemsStart += 20
                apiService?.getItemsByUser(size: size, start: itemsStart, id: currentUser!.id!) { i in
                                                
                        if (i?.count)! < 20 { // Check if all replies found
                            print("API: No new items")
                            self.itemsReachedEnd = true
                        }
                        
                        self.itemsArray += i!
                        
                        self.tableView.reloadData()
                        self.isLoading = false
                        self.tableView.tableFooterView?.isHidden = true
                    }
                }
                break
            case 1:
                if activityArray.count >= 21
                    && activityReachedEnd == false { // Need atleast 21 replies
                    
                    self.isLoading = true
                    self.tableView.tableFooterView?.isHidden = false
                    
                    // Add 20 to load the next posts
                    self.activityStart += 20
                    apiService?.getRepliesByUser(size: size, start: activityStart,
                                               id: currentUser!.id!) { r in
                                                
                        if (r?.count)! < 20 { // Check if all replies found
                            print("API: No new replies")
                            self.activityReachedEnd = true
                        }
                        
                        self.activityArray += r!
                        
                        self.tableView.reloadData()
                        self.isLoading = false
                        self.tableView.tableFooterView?.isHidden = true
                    }
                }
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
            returnValue = itemsArray.count
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
            let item = itemsArray[indexPath.row] as Item
            
            if let titleLabel = cell.viewWithTag(401) as? UILabel {
                titleLabel.text = item.title
            }
            
            if let textLabel = cell.viewWithTag(402) as? UILabel {
                textLabel.text = item.text
            }
            
            if let dateLabel = cell.viewWithTag(403) as? UILabel {
                dateLabel.text = item.dateCreated?.timeAgoSimple
            }
            
            break
        case 1:
            let reply = activityArray[indexPath.row] as Reply
            
            if let titleLabel = cell.viewWithTag(401) as? UILabel {
                titleLabel.text = reply.user.name + " wil helpen met: "
            }
            
            if let textLabel = cell.viewWithTag(402) as? UILabel {
                textLabel.text = reply.post.title
            }
            
            if let dateLabel = cell.viewWithTag(403) as? UILabel {
                dateLabel.text = reply.dateCreated.timeAgoSimple
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


