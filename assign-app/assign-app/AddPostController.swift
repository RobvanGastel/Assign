//
//  AddPostController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 26/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

/// Controller to create new Posts
/// TODO: Add ERROR/SUCCESS message
class AddPostController: UIViewController, UITextViewDelegate {

    @IBOutlet weak var titleField: UITextField!
    @IBOutlet weak var descriptionText: UITextView!
    @IBOutlet weak var counterField: UITitle!
    @IBOutlet weak var addPostButton: UIBarButtonItem!
    
    // API service
    var apiService: ApiService?
    
    // Refreshing Posts delegate
    weak var delegate: RefreshPostsDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Init API service
        apiService = ApiService()
        
        // Initializes the delegate
        descriptionText.delegate = self
        
        // Open up keyboard on load
        titleField.becomeFirstResponder()
    }
    
    /// Set StatusBartStyle to .default and sets navigationbar.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        // Layout settings
        view.backgroundColor = UIColor(red: 245/255, green: 245/255, blue: 245/255, alpha: 1)
        self.navigationController?.navigationBar.barTintColor = UIColor(red: 245/255, green: 245/255, blue: 245/255, alpha: 1)
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")
    }
    
    /// TextView Delegates manages the character limit and the counter
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        
        let maxCharacter: Int = 255 // Max character for the textView
        
        let counter = (textView.text.utf16.count) + text.utf16.count - range.length
        let amount = maxCharacter - counter
        
        // Makes sure the counter doesnt drop below 0
        switch amount {
        case 246 ..< 256:
            counterField.textColor = UIColor(red: 0.66, green: 0.66, blue: 0.66, alpha: 1)
            counterField.text = String(maxCharacter - counter)
            addPostButton.tintColor = UIColor(red: 0.64, green: 0.64, blue: 0.64, alpha: 1)
        case 15 ..< 246:
            counterField.textColor = UIColor(red: 0.66, green: 0.66, blue: 0.66, alpha: 1)
            counterField.text = String(maxCharacter - counter)
            addPostButton.tintColor = UIColor(red: 1.0, green: 0.6, blue: 0.16, alpha: 1)
        case 7 ..< 15:
            counterField.textColor = UIColor.orange
            counterField.text = String(maxCharacter - counter)
        case 1 ..< 7:
            counterField.textColor = UIColor.red
            counterField.text = String(maxCharacter - counter)
        default:
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
        if titleField.text != "" && descriptionText.text != "" && descriptionText.text != "Geef een beschrijving" {
            
            // Add post API call with the API Service
            apiService?.addPost(title: titleField.text!, description: descriptionText.text!) { success in
                
                if(success == true) {
                    // Navigate back to the previous view
                    self.delegate?.refreshPosts()
                    self.navigationController?.popViewController(animated: true)
                    
                    // TODO: Add SUCCESS message
                } else {
                    // TODO: Add ERROR message
                }
            }
        } else {
            // TODO: Add ERROR/SUCCESS message
        }
    }
    
    @IBAction func backClick(_ sender: Any) {
        view.endEditing(true)
        self.navigationController?.popViewController(animated: true)
    }
}
