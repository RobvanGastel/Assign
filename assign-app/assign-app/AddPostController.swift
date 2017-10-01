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
    
    // Refreshing Posts delegate
    weak var delegate: PostsRefreshDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Hides the keyboard when tapping on the screen
        

        // Init API service
        apiService = ApiService()
        
        // Initializes the delegate
        descriptionText.delegate = self
        
        // Open up keyboard on load
        titleField.becomeFirstResponder()
        
        // Layout settings
        view.backgroundColor = UIColor(red: 1, green: 1, blue: 1, alpha: 1)
        self.navigationController?.navigationBar.barTintColor = UIColor(red: 1, green: 1, blue: 1, alpha: 1)
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")
    }
    
    /// TextView Delegates manages the character limit and the counter
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        
        let maxCharacter: Int = 255 // Max character for the textView
        
        let counter = (textView.text.utf16.count) + text.utf16.count - range.length
        let amount = maxCharacter - counter
        
        // Makes sure the counter doesnt drop below 0
        if(amount >= 12) {
            counterField.textColor = UIColor(red: 0.66, green: 0.66, blue: 0.66, alpha: 1)
            counterField.text = String(maxCharacter - counter)
        } else if(amount >= 6) {
            counterField.textColor = UIColor.orange
            counterField.text = String(maxCharacter - counter)
        } else if(amount >= 3) {
            counterField.textColor = UIColor.red
            counterField.text = String(maxCharacter - counter)
        } else if(amount >= 0) {
            counterField.textColor = UIColor.red
            counterField.text = String(maxCharacter - counter)
        } else {
            counterField.textColor = UIColor.red
            counterField.text = "0"
        }
        
        return (textView.text?.utf16.count ?? 0) + text.utf16.count - range.length <= maxCharacter
    }
    
    // When the user starts editing will empty the textView
    func textViewDidBeginEditing(_ textView: UITextView) {
        if textView.textColor == UIColor(red: 0.67, green: 0.67, blue: 0.67, alpha: 1) {
            textView.text = nil
            textView.textColor = UIColor(red: 0.44, green: 0.44, blue: 0.44, alpha: 1)
        }
    }
    
    // Whent he user stops editing and the textView is still empty adds
    // the text again
    func textViewDidEndEditing(_ textView: UITextView) {
        if textView.text.isEmpty {
            textView.text = "Geef een beschrijving"
            textView.textColor = UIColor(red: 0.67, green: 0.67, blue: 0.67, alpha: 1)
        }
    }

    /// Add post function
    @IBAction func addPost(_ sender: Any) {
        // Check if the fields are filled
        if titleField.text != "" && descriptionText.text != "" {

            // Add post API call with the API Service
            apiService?.addPost(title: titleField.text!, description: descriptionText.text!) { success in
                if(success == true) {
                    // Navigate back to the previous view
                    self.delegate?.refreshPosts()
                    self.dismiss(animated: true, completion: nil)
                    
                    // TODO Add SUCCESS message
                } else {
                    // TODO Add ERROR message
                }
            }
        } else {
            // TODO Add fill in the fields message
        }
    }
    
    @IBAction func backClick(_ sender: Any) {
        
        view.endEditing(true)
        dismiss(animated: true, completion: nil)
    }
}
