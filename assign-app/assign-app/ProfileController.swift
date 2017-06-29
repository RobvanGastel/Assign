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
    
    var apiService: ApiService?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        apiService = ApiService()
        
        self.apiService?.getCurrentUser() { response in
            self.nameLabel.text = response?.name
        }
    
    }
    
    
}
