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
/// TODO: return ERROR message.
class LoginController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var email: UITextField!
    @IBOutlet weak var password: UITextField!
    @IBOutlet weak var loginButton: UIButton!
    @IBOutlet weak var ScrollView: UIScrollView!
    @IBOutlet weak var emailLabel: UILabel!
    @IBOutlet weak var wachtwoordLabel: UILabel!
    @IBOutlet weak var emailError: UILabel!
    @IBOutlet weak var wachtwoordError: UILabel!
    
    // The API & Auth service
    var apiService: ApiService?
    var authService: AuthService?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Hides the keyboard when tapping on the screen
        self.hideKeyboardWhenTappedAround()
        
        // Enabled scroll
        ScrollView.isScrollEnabled = true
        ScrollView.alwaysBounceVertical = true
        
        // Init of the services
        apiService = ApiService()
        authService = AuthService()
        
        // Initializes the delegate
        self.password.delegate = self
        
        // Hide Label and Error of input fields
        emailLabel.layer.opacity = 0
        wachtwoordLabel.layer.opacity = 0
        emailError.layer.opacity = 0
        wachtwoordError.layer.opacity = 0
    }
    
    // Add style and scroll on typing
    @IBAction func emailBegin(_ sender: UIInput) {
        ScrollView.setContentOffset(CGPoint(x: 0, y: 120), animated: true)
        email.layer.shadowColor = UIColor(red: 1, green: 0.5, blue: 0.156, alpha: 1).cgColor
        UIView.animate(withDuration: 0.3) {
            self.emailLabel.layer.opacity = 1
            self.emailError.layer.opacity = 0
        }
    }
    @IBAction func emailEnd(_ sender: UIInput) {
        ScrollView.setContentOffset(CGPoint(x: 0, y: 0), animated: true)
        email.layer.shadowColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
        if email.text == "" {
            UIView.animate(withDuration: 0.3) {
                self.emailLabel.layer.opacity = 0
            }
        }
    }
    
    @IBAction func passwordBegin(_ sender: UIInput) {
        self.ScrollView.setContentOffset(CGPoint(x: 0, y: 120), animated: true)
        self.password.layer.shadowColor = UIColor(red: 1, green: 0.5, blue: 0.156, alpha: 1).cgColor
        UIView.animate(withDuration: 0.3) {
            self.wachtwoordLabel.layer.opacity = 1
            self.wachtwoordError.layer.opacity = 0
        }
    }
    @IBAction func passwordEnd(_ sender: UIInput) {
        ScrollView.setContentOffset(CGPoint(x: 0, y: 0), animated: true)
        password.layer.shadowColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
        if password.text == "" {
            UIView.animate(withDuration: 0.3) {
                self.wachtwoordLabel.layer.opacity = 0
            }
        }
    }
    
    /// On the last return triggers the authenticate method and 'Next' will tab to password.
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        
        if let nextField = textField.superview?.viewWithTag(textField.tag + 1) as? UITextField {
            nextField.becomeFirstResponder()
        } else {
            self.password.resignFirstResponder()
            authenticate(email: email.text!, password: password.text!)
        }
        return true
    }

    /// Login action from the view.
    @IBAction func login(_ sender: Any) {
        // Set loading indicator true
        loginButton.loadingIndicator(show: true, text: "")
        
        authenticate(email: email.text!, password: password.text!)
    }

    /// Authenticates the user against the API.
    func authenticate(email: String, password : String) {
        UIView.animate(withDuration: 0.2) {
            self.emailError.layer.opacity = 0
            self.wachtwoordError.layer.opacity = 0
        }
        
        // Empty check on the fields
        if email != "" && password != "" {
            
            // Authenticate API call
            authService?.authenticate(email: email, password: password) { success in // "rob@mail.nl" "max"
                if(success == true) {
                    // Debug messageToken
                    // print(Messaging.messaging().fcmToken!)
                    
                    self.apiService?.registerDevice(token: Messaging.messaging().fcmToken!) { response in }
                    
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
                    self.loginButton.loadingIndicator(show: false, text: "Log in")
                    self.emailError.text = "Verkeerde inloggegevens"
                    UIView.animate(withDuration: 0.3) {
                        self.emailError.layer.opacity = 1
                    }
                }
            }
        } else if email == "" && password != "" {
            self.loginButton.loadingIndicator(show: false, text: "Log in")
            emailError.text = "Vul een e-mailadres in"
            UIView.animate(withDuration: 0.3) {
                self.emailError.layer.opacity = 1
            }
        } else if email != "" && password == "" {
            self.loginButton.loadingIndicator(show: false, text: "Log in")
            UIView.animate(withDuration: 0.3) {
                self.wachtwoordError.layer.opacity = 1
            }
        } else {
            self.loginButton.loadingIndicator(show: false, text: "Log in")
            emailError.text = "Vul een e-mailadres in"
            UIView.animate(withDuration: 0.3) {
                self.emailError.layer.opacity = 1
                self.wachtwoordError.layer.opacity = 1
            }
        }

    }
    
    /// Redirect to another view.
    func redirectViewController(identifier: String) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: identifier)
        self.present(vc, animated: false, completion: nil)
    }
}
