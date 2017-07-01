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
    
    // The provided data from the segue 
    var currentUser: User?
    
    let overviewArray: [Post] = []
    let assignmentsArray: [Post] = []
    let activityArray: [Post] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Init API service
        apiService = ApiService()
        
        // Retrieve User
        currentUser = Storage.getUser()
        
        // TODO set multiple fields
        self.nameLabel.text = currentUser?.name
        
        // TODO make API call to fill the tables
        
        // Set delegates
        tableView.delegate = self
        tableView.dataSource = self
        
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
    
    @IBAction func segmentedControlActionChanged(_ sender: Any) {
        self.tableView.reloadData()
    }
    
}
