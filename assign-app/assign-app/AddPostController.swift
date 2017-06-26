//
//  AddPostController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 26/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class AddPostController: UIViewController {

    @IBOutlet weak var titleField: UITextField!
    @IBOutlet weak var descriptionText: UITextView!
    
    var apiService: ApiService?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        apiService = ApiService()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func addPost(_ sender: Any) {
        if titleField.text != "" && descriptionText.text != "" {
            apiService?.addPost(title: titleField.text!, description: descriptionText.text!) { success in
                if(success == true) {
                    
                } else {
                    //TODO Add error to page
                }
            }
        } else {
            //TODO return error to page
        }
    }
}
