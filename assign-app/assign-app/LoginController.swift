//
//  LoginController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 18/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class LoginController: UIViewController {

    @IBOutlet weak var email: UITextField!
    @IBOutlet weak var password: UITextField!
    
    var apiService: ApiService?
    var authService: AuthService?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        apiService = ApiService()
        authService = AuthService()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    @IBAction func login(_ sender: Any) {
        if email.text == "" && password.text == "" {
            authenticate(email: email.text!, password: password.text!)
        } else {
            //TODO return error to page
        }
    }
    
    /// Authenticates the user against the API.
    /// 
    /// TODO Add getter/setter for the User data.
    func authenticate(email: String, password : String) {

        authService?.authenticate(email: "admin@mail.nl", password: "admin") { success in
            if(success == true) {
                Storage.setCredentials(credentials: Credentials(email: "admin@mail.nl", password: "admin"))
                
                self.apiService?.getCurrentUser() { response in
                    //TODO add Data to Core Data
                    print("User: username: \(String(describing: response?.email)), id: \(String(describing: response?.id))")
                }
                
                let storyBoard : UIStoryboard = UIStoryboard(name: "Main", bundle:nil)
                let nextViewController = storyBoard.instantiateViewController(withIdentifier: "PostsNavigationController") as! UINavigationController
                self.present(nextViewController, animated:true, completion:nil)
                
            } else {
                //TODO Add error to page
            }
        }
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
