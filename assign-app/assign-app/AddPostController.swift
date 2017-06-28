//
//  AddPostController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 26/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

/// Controller to create new Posts
class AddPostController: UIViewController, UITextViewDelegate {

    @IBOutlet weak var titleField: UITextField!
    @IBOutlet weak var descriptionText: UITextView!
    @IBOutlet weak var counterField: UITitle!

    // API service
    var apiService: ApiService?

    override func viewDidLoad() {
        super.viewDidLoad()
        // Hides the keyboard when tapping on the screen
        self.hideKeyboardWhenTappedAround()

        // Init API service
        apiService = ApiService()
        
        // description delegate
        descriptionText.delegate = self
    }
    
    /// Set StatusBartStyle to default
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        UIApplication.shared.statusBarStyle = .default
    }
    
    /// TextView Delegates manages the character limit and the counter
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        
        // Max character for the textView
        let maxCharacter: Int = 140
        
        // Counter calculations
        let counter = (textView.text.utf16.count) + text.utf16.count - range.length
        let amount = maxCharacter - counter
        
        // Makes sure the counter doesnt drop below 0
        if(amount >= 0) {
            counterField.textColor = UIColor.lightGray
            counterField.text = String(maxCharacter - counter)
        } else {
            counterField.textColor = UIColor.red
            counterField.text = "0"
        }
        
        return (textView.text?.utf16.count ?? 0) + text.utf16.count - range.length <= maxCharacter
    }
    
    func textViewDidBeginEditing(_ textView: UITextView) {
        if textView.textColor == UIColor.lightGray {
            textView.text = nil
            textView.textColor = UIColor.black
        }
    }
    
    func textViewDidEndEditing(_ textView: UITextView) {
        if textView.text.isEmpty {
            textView.text = "Geef een beschrijving"
            textView.textColor = UIColor.lightGray
        }
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
