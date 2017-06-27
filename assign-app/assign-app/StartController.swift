//
//  StartController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 26/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

/// Controller to register the user.
class StartController: UIViewController {

    // API service
    var authService: AuthService?
    var apiService: ApiService?

    override func viewDidLoad() {
        super.viewDidLoad()

        // Init API service
        authService = AuthService()
        apiService = ApiService()
        
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)

        // Checks if the user has loggedin in the past
        if(Storage.getLoggedIn()) {

            // Authenticate the user with storage variables
            authService?.authenticate(email: Storage.getCredentials().email!,
                                      password: Storage.getCredentials().password!) { success in

                // If authentication succeded redirect to next view
                if(success == true) {
                    self.redirectViewController(identifier: "PostsNavigationController")

                   // TODO Add retrieve user
                   // self.apiService?.getCurrentUser() { response in
                   //      print("User: username: \(String(describing: response?.email)), id: \(String(describing: response?.id))")
                   //}

                // Redirect to login view
                } else {
                    self.redirectViewController(identifier: "LoginController")
                }
            }

        } else {
            // Redirect to login view
            self.redirectViewController(identifier: "LoginController")
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    /// Redirect to another view.
    func redirectViewController(identifier: String) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: identifier)
        self.present(vc, animated: true, completion: nil)
    }
}
