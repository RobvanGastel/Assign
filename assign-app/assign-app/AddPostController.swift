//
//  AddPostController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 26/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

/// Controller to create new Posts
class AddPostController: UIViewController {

    @IBOutlet weak var titleField: UITextField!
    @IBOutlet weak var descriptionText: UITextView!

    // API service
    var apiService: ApiService?

    override func viewDidLoad() {
        super.viewDidLoad()

        // Init API service
        apiService = ApiService()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    /// Set StatusBartStyle
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        UIApplication.shared.statusBarStyle = .default
    }

    /// Add post function
    @IBAction func addPost(_ sender: Any) {
        // Check if the fields are filled
        if titleField.text != "" && descriptionText.text != "" {

            // Add post API call with the API Service
            apiService?.addPost(title: titleField.text!, description: descriptionText.text!) { success in
                if(success == true) {
                    // TODO On succes navigate back to the previous view
                    // TODO Add SUCCESS message
                } else {
                    // TODO Add ERROR message
                }
            }
        } else {
            // TODO Add fill in the fields message
        }
    }
}
