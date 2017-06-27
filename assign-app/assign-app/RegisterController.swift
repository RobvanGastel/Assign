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
    
    //The Auth service
    var authService: AuthService?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.hideKeyboardWhenTappedAround()
        
        //Init of the services
        authService = AuthService()
        self.codeField.delegate = self as UITextFieldDelegate
        self.codeField.returnKeyType = UIReturnKeyType.go
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        register(email: emailField.text!, password: passwordField.text!, code: codeField.text!)
        return true
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    @IBAction func register(_ sender: Any) {
        register(email: emailField.text!, password: passwordField.text!, code: codeField.text!)
    }

    func register(email: String, password: String, code: String) {
        if email != "" && password != "" && code != "" {
            authService?.register(email: emailField.text!, password: passwordField.text!, code: codeField.text!) { success in
                if(success == true) {
                    
                    self.redirectViewController(identifier: "LoginController")
                    
                } else {
                    // TODO return ERROR message
                }
            }
        }
    }
    
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
