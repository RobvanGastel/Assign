//
//  SettingsController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 01/07/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

/// TODO: Implement settings
class SettingsController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    /// Set StatusBartStyle to default
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        // Layout settings
        UIApplication.shared.statusBarStyle = .default
        self.navigationController?.setNavigationBarHidden(false, animated: true)
        view.backgroundColor = UIColor(red: 245/255, green: 245/255, blue: 245/255, alpha: 1)
        self.navigationController?.navigationBar.barTintColor = UIColor(red: 245/255, green: 245/255, blue: 245/255, alpha: 1)
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")
    }
}
