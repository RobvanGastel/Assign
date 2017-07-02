//
//  ProfileDetailController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 30/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class ProfileDetailController: UIViewController {
    
    @IBOutlet weak var nameLabel: UILabel!
    
    // The provided data from the segue
    var currentUser: User?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // TODO set multiple fields
        self.nameLabel.text = currentUser?.name
    }
}
