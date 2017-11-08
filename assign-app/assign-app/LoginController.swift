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
        emailLabel.isHidden = true
        wachtwoordLabel.isHidden = true
        emailError.isHidden = true
        wachtwoordError.isHidden = true
    }
    
    // Add style and scroll on typing
    @IBAction func emailBegin(_ sender: UIInput) {
        email.layer.shadowColor = UIColor(red: 1, green: 0.5, blue: 0.156, alpha: 1).cgColor
        emailLabel.layer.opacity = 1
        ScrollView.setContentOffset(CGPoint(x: 0, y: 120), animated: true)
    }
    @IBAction func emailEnd(_ sender: UIInput) {
        email.layer.shadowColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
        emailLabel.layer.opacity = 0
        ScrollView.setContentOffset(CGPoint(x: 0, y: 0), animated: true)
    }
    
    @IBAction func passwordBegin(_ sender: UIInput) {
        password.layer.shadowColor = UIColor(red: 1, green: 0.5, blue: 0.156, alpha: 1).cgColor
        wachtwoordLabel.layer.opacity = 1
        ScrollView.setContentOffset(CGPoint(x: 0, y: 120), animated: true)
    }
    @IBAction func passwordEnd(_ sender: UIInput) {
        password.layer.shadowColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
        wachtwoordLabel.layer.opacity = 0
        ScrollView.setContentOffset(CGPoint(x: 0, y: 0), animated: true)
    }
    
    /// On the last return triggers the authenticate method and 'Next' will tab to password.
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        if (textField == self.email) {
            self.email.becomeFirstResponder()
        }
        else if (textField == self.password) {
            textField.resignFirstResponder()
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
        
        // Empty check on the fields
        if email == "" && password == "" {
            
            // Authenticate API call
            authService?.authenticate(email: "max@mail.nl", password: "max") { success in
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
                    // TODO: return ERROR message
                }
            }
        } else {
            // TODO: return ERROR message.
        }

    }
    
    /// Redirect to another view.
    func redirectViewController(identifier: String) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: identifier)
        self.present(vc, animated: false, completion: nil)
    }
}
