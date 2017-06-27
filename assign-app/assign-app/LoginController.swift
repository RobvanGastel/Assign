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
        self.hideKeyboardWhenTappedAround()

        // Init of the services
        apiService = ApiService()
        authService = AuthService()
        
        self.password.delegate = self
        self.password.returnKeyType = UIReturnKeyType.go
    }

    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        authenticate(email: email.text!, password: password.text!)
        return true
    }

    /// Login action from the view.
    @IBAction func login(_ sender: Any) {
        authenticate(email: email.text!, password: password.text!)
    }
    
    @IBAction func registerRedirect(_ sender: Any) {
        self.redirectViewController(identifier: "RegisterController")
    }

    /// Authenticates the user against the API.
    ///
    /// TODO Add getter/setter for the User data.
    func authenticate(email: String, password : String) {
        
        // Check if the fields are filled
        if email == "" && password == "" {
            
            authService?.authenticate(email: "admin@mail.nl", password: "admin") { success in
                if(success == true) {
                    
                    self.apiService?.getCurrentUser() { response in
                        // TODO add Data to Core Data
                        print("User: username: \(String(describing: response?.email)), id: \(String(describing: response?.id))")
                    }
                    
                    self.redirectViewController(identifier: "PostsNavigationController")
                    
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
