//
//  StartController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 26/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class StartController: UIViewController {

    var authService: AuthService?
    var apiService: ApiService?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        authService = AuthService()
        apiService = ApiService()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        
        // Checks if the user is logged in
        // if true it opens the Tab Bar
        // if flase it opens the sign up and login screen
        if(Storage.getLoggedIn()) {
            
            // Authenticate logged in user
            authService?.authenticate(email: Storage.getCredentials().email!,
                                      password: Storage.getCredentials().password!) { success in
                
                // If authentication succeded retrieve user
                if(success == true) {
                    self.redirectViewController(identifier: "PostsNavigationController")
                    
//                    self.apiService?.getCurrentUser() { response in
//                        //TODO add Data to Core Data
//                        print("User: username: \(String(describing: response?.email)), id: \(String(describing: response?.id))")
//                    }
                    
                // Else prompt authentication
                } else {
                    self.redirectViewController(identifier: "LoginController")
                }
            }
            
        } else {
            // Else prompt authentication
            self.redirectViewController(identifier: "LoginController")
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func redirectViewController(identifier: String) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: identifier)
        self.present(vc, animated: true, completion: nil)
    }
}
