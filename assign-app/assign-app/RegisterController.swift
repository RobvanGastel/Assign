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
    
    // The Auth service
    var authService: AuthService?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Hides the keyboard when tapping on the screen
        self.hideKeyboardWhenTappedAround()
        
        //Init of the services
        authService = AuthService()
        
        // Sets the delegate on the textField and creates a custom returnType
        self.codeField.delegate = self as UITextFieldDelegate
        self.codeField.returnKeyType = UIReturnKeyType.go
    }
    
    /// On the last return triggers the register method.
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        register(email: emailField.text!, password: passwordField.text!, code: codeField.text!)
        return true
    }
    
    /// The register button in the view.
    @IBAction func register(_ sender: Any) {
        register(email: emailField.text!, password: passwordField.text!, code: codeField.text!)
    }

    /// The register call to the backend.
    func register(email: String, password: String, code: String) {
        
        // Empty check on the fields
        if email != "" && password != "" && code != "" {
            
            // Register API call
            authService?.register(email: emailField.text!, password: passwordField.text!, code: codeField.text!) { success in
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
