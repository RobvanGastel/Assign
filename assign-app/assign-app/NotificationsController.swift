//
//  NotificationsController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 01/07/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class NotificationsController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Layout settings
        view.backgroundColor = UIColor(red: 0.98, green: 0.98, blue: 0.98, alpha: 1)
        self.navigationController?.navigationBar.barTintColor = UIColor(red: 0.98, green: 0.98, blue: 0.98, alpha: 1)
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")
    }

    /// Set StatusBartStyle to default.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        UIApplication.shared.statusBarStyle = .default
        
        self.navigationController?.navigationBar.barTintColor = UIColor(red: 0.98, green: 0.98, blue: 0.98, alpha: 1)
    }

    // MARK: - Table view with notifications

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 0
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return 0
    }
}
