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
    }
}
