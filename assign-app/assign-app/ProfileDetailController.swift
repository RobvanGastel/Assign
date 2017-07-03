//
//  ProfileDetailController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 30/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit
import AlamofireImage

class ProfileDetailController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var profileImage: UIProfile!
    
    @IBOutlet weak var backImage: UIImageView!
    @IBOutlet weak var segmentedControl: UISegmentedControl!
    @IBOutlet weak var tableView: UITableView!
    
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
        tapRec.addTarget(self, action: #selector(ProfileDetailController.backTap))
        backImage.addGestureRecognizer(tapRec)
        
        
        // Layout settings
        self.navigationController?.navigationBar.isHidden = true
    }
    
    /// Set profile of the User.
    func setProfile() {
        
        // TODO set multiple fields
        self.nameLabel.text = currentUser?.name
        
        let url = URL(string: (currentUser?.profileImage)!)!
        let filter = AspectScaledToFillSizeFilter(size: profileImage.frame.size)
        profileImage.af_setImage(withURL: url, filter: filter)
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
    func backTap() {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: "SettingsNavigationController")
        self.present(vc, animated: true, completion: nil)
    }
    
    /// When changed to another tableView.
    @IBAction func segmentedControlActionChanged(_ sender: Any) {
        self.tableView.reloadData()
    }
    
    /// Set StatusBartStyle to .lightContent.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        UIApplication.shared.statusBarStyle = .lightContent
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        // Show navigation bar again on returning to other views
        self.navigationController?.navigationBar.isHidden = false
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
        
        return cell
    }

}
