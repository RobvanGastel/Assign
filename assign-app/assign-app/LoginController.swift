//
//  LoginController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 18/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

/// Controller to let the user authenticate.
class LoginController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var email: UITextField!
    @IBOutlet weak var password: UITextField!

    // The API & Auth service
    var apiService: ApiService?
    var authService: AuthService?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Hides the keyboard when tapping on the screen
        self.hideKeyboardWhenTappedAround()

        // Init of the services
        apiService = ApiService()
        authService = AuthService()
        
        // Initializes the delegate
        self.password.delegate = self
    }

    /// On the last return triggers the authenticate method.
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        authenticate(email: email.text!, password: password.text!)
        return true
    }

    /// Login action from the view.
    @IBAction func login(_ sender: Any) {
        authenticate(email: email.text!, password: password.text!)
    }
    
    /// Redirect to the register view.
    ///
    /// TODO add matching animation
    @IBAction func registerRedirect(_ sender: Any) {
        self.redirectViewController(identifier: "RegisterController")
    }

    /// Authenticates the user against the API.
    func authenticate(email: String, password : String) {
        
        // Empty check on the fields
        if email == "" && password == "" {
            
            // Authenticate API call
            authService?.authenticate(email: "admin@mail.nl", password: "admin") { success in
                if(success == true) {
                    
                    // Get the logged in user
                    self.apiService?.getCurrentUser() { response in
                        print("User: username: \(String(describing: response?.email)), id: \(String(describing: response?.id))")
                    }
                    
                    // Redirect to overview
                    let storyboard = UIStoryboard(name: "Main", bundle: nil)
                    let vc = storyboard.instantiateViewController(withIdentifier: "PostsTabBarController") as! UITabBarController
                    vc.selectedIndex = 1
                    self.present(vc, animated: true, completion: nil)
                    
                } else {
                    
                    // TODO return ERROR message
                }
            }
        } else {
            
            // TODO return ERROR message.
        }

    }
    
    /// Redirect to another view.
    func redirectViewController(identifier: String) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: identifier)
        self.present(vc, animated: false, completion: nil)
    }
}
