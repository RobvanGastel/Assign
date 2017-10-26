//
//  LoginController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 18/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit
import Firebase

/// Controller to let the user authenticate.
class LoginController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var email: UITextField!
    @IBOutlet weak var password: UITextField!
    @IBOutlet weak var loginButton: UIButton!

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
    
    
    // On typing inputs
    @IBAction func emailBegin(_ sender: Any) {
        email.layer.shadowColor = UIColor(red: 1, green: 0.5, blue: 0.156, alpha: 1).cgColor
    }
    @IBAction func emailEnd(_ sender: Any) {
        email.layer.shadowColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
    }
    
    @IBAction func passwordBegin(_ sender: Any) {
        password.layer.shadowColor = UIColor(red: 1, green: 0.5, blue: 0.156, alpha: 1).cgColor
    }
    @IBAction func passwordEnd(_ sender: Any) {
        password.layer.shadowColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
    }
    
    
    /// On the last return triggers the authenticate method and 'Next' will tab to password.
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        if (textField == self.email) {
            self.email.becomeFirstResponder()
            print("pls yes")
        }
        else if (textField == self.password) {
            textField.resignFirstResponder()
            authenticate(email: email.text!, password: password.text!)
            print("pls no")
        }
        return true
    }

    /// Login action from the view.
    @IBAction func login(_ sender: Any) {
        // Set loading indicator true
        loginButton.loadingIndicator(show: true, text: "")
        
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
            authService?.authenticate(email: "max@mail.nl", password: "max") { success in
                if(success == true) {
                    
                    // TODO Handle check
                    // print(Messaging.messaging().fcmToken!)
                    // TODO Handle possible exceptions
                    self.apiService?.registerDevice(token: Messaging.messaging().fcmToken!) { response in
                        
                    }
                    
                    // Get the logged in user
                    self.apiService?.getCurrentUser() { response in
                        print("User: username: \(String(describing: response?.email)), id: \(String(describing: response?.id))")
                    }
                    
                    
                    self.loginButton.loadingIndicator(show: false, text: "Login")
                    
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
