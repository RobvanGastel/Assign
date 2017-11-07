//
//  RegisterController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 26/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

/// Controller to register the user.
class RegisterController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var emailField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    @IBOutlet weak var codeField: UITextField!
    @IBOutlet weak var nameField: UITextField!
    @IBOutlet weak var ScrollView: UIScrollView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var emailLabel: UILabel!
    @IBOutlet weak var wachtwoordLabel: UILabel!
    @IBOutlet weak var codeLabel: UILabel!
    
    // The Auth service
    var authService: AuthService?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Hides the keyboard when tapping on the screen
        self.hideKeyboardWhenTappedAround()
        
        // Enabled scroll
        ScrollView.isScrollEnabled = true
        ScrollView.alwaysBounceVertical = true
        
        //Init of the services
        authService = AuthService()
        
        // Initializes the delegate
        self.nameField.delegate = self
        
        nameLabel.layer.opacity = 0
        emailLabel.layer.opacity = 0
        wachtwoordLabel.layer.opacity = 0
        codeLabel.layer.opacity = 0
    }

    // On typing inputs
    // TODO: Add abstract class for on type effect
    @IBAction func nameFieldBegin(_ sender: Any) {
        nameField.layer.shadowColor = UIColor(red: 1, green: 0.5, blue: 0.156, alpha: 1).cgColor
        nameLabel.layer.opacity = 1
        ScrollView.setContentOffset(CGPoint(x: 0, y: 80), animated: true)
    }
    @IBAction func nameFieldEnd(_ sender: Any) {
        nameField.layer.shadowColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
        nameLabel.layer.opacity = 0
        ScrollView.setContentOffset(CGPoint(x: 0, y: 0), animated: true)
    }
    
    @IBAction func emailFieldBegin(_ sender: Any) {
        emailField.layer.shadowColor = UIColor(red: 1, green: 0.5, blue: 0.156, alpha: 1).cgColor
        emailLabel.layer.opacity = 1
        ScrollView.setContentOffset(CGPoint(x: 0, y: 100), animated: true)
    }
    @IBAction func emailFieldEnd(_ sender: Any) {
        emailField.layer.shadowColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
        emailLabel.layer.opacity = 0
        ScrollView.setContentOffset(CGPoint(x: 0, y: 0), animated: true)
    }
    
    @IBAction func passwordFieldBegin(_ sender: Any) {
        passwordField.layer.shadowColor = UIColor(red: 1, green: 0.5, blue: 0.156, alpha: 1).cgColor
        wachtwoordLabel.layer.opacity = 1
        ScrollView.setContentOffset(CGPoint(x: 0, y: 120), animated: true)
    }
    @IBAction func passwordFieldEnd(_ sender: Any) {
        passwordField.layer.shadowColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
        wachtwoordLabel.layer.opacity = 0
        ScrollView.setContentOffset(CGPoint(x: 0, y: 0), animated: true)
    }
    
    @IBAction func codeFieldBegin(_ sender: Any) {
        codeField.layer.shadowColor = UIColor(red: 1, green: 0.5, blue: 0.156, alpha: 1).cgColor
        codeLabel.layer.opacity = 1
        ScrollView.setContentOffset(CGPoint(x: 0, y: 140), animated: true)
    }
    @IBAction func codeFieldEnd(_ sender: Any) {
        codeField.layer.shadowColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
        codeLabel.layer.opacity = 0
        ScrollView.setContentOffset(CGPoint(x: 0, y: 0), animated: true)
    }
    
    
    /// On the last return triggers the register method.
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        register(email: emailField.text!, password: passwordField.text!, code: codeField.text!, name: nameField.text!)
        return true
    }
    
    /// The register button in the view.
    @IBAction func register(_ sender: Any) {
        register(email: emailField.text!, password: passwordField.text!, code: codeField.text!, name: nameField.text!)
    }

    /// The register call to the backend.
    func register(email: String, password: String, code: String, name: String) {
        
        // Empty check on the fields
        if email != "" && password != "" && code != "" && name != "" {
            
            // Register API call
            authService?.register(email: email, password: password, code: code, name: name)
            { success in
                if(success == true) {
                    
                    // Redirects the view to login
                    self.redirectViewController(identifier: "LoginController")
                    
                } else {
                    // TODO return ERROR message
                }
            }
        }
    }
    
    /// Redirects the view to login.
    ///
    /// TODO add matching animation
    @IBAction func loginRedirect(_ sender: Any) {
        self.redirectViewController(identifier: "LoginController")
    }
    
    /// Redirect to another view.
    func redirectViewController(identifier: String) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: identifier)
        self.present(vc, animated: false, completion: nil)
    }
}
