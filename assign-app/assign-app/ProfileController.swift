//
//  ProfileController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 29/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class ProfileController: UIViewController {

    @IBOutlet weak var nameLabel: UILabel!
    
    // The API service
    var apiService: ApiService?
    
    // The provided data from the segue 
    var currentUser: User?
    
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
        
        // Set the layout of the view
        view.backgroundColor = UIColor(red: 0.98, green: 0.98, blue: 0.98, alpha: 1)
        
        // Get rid of the nasty nav border
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")
    }
}
