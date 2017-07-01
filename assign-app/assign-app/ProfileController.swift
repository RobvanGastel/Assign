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
    
    // DEMO dummy data
    let privateList:[String] = ["Private item 1","Private item 2"]
    let friendsAndFamily:[String] = ["Friend item 1","Friend item 2", "Friends item 3"]
    let publicList:[String] = ["Public item 1", "Public item 2", "Public item 3", "Public item 4"]
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Init API service
        apiService = ApiService()
        
        // Retrieve User
        currentUser = Storage.getUser()
        
        if currentUser != nil {
            // TODO set multiple fields
            self.nameLabel.text = currentUser?.name
        }
        
        tableView.delegate = self
        tableView.dataSource = self
        
        // Set the layout of the view
        view.backgroundColor = UIColor(red: 0.98, green: 0.98, blue: 0.98, alpha: 1)
        
        // Get rid of the nasty nav border
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
            returnValue = privateList.count
            break
        case 1:
            returnValue = friendsAndFamily.count
            break
            
        case 2:
            returnValue = publicList.count
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
            if let nameLabel = cell.viewWithTag(501) as? UILabel {
            nameLabel.text = privateList[indexPath.row]
            }
            break
        case 1:
            if let nameLabel = cell.viewWithTag(501) as? UILabel {
                nameLabel.text = friendsAndFamily[indexPath.row]
            }
            break
            
        case 2:
            if let nameLabel = cell.viewWithTag(501) as? UILabel {
                nameLabel.text = publicList[indexPath.row]
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
