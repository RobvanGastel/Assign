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
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func login(_ sender: Any) {
        if email.text != "" && password.text != "" {
            authenticate(email: email.text!, password: password.text!)
        } else {
            //TODO return error to login
        }
    }
    
    func authenticate(email: String, password : String) {
        let authService = AuthService()
        if authService.authenticate(email: "admin@mail.nl", password: "admin") {
            //TODO redirect to Posts
            Storage.setCredentials(credentials: Credentials(email: email, password: password))
        } else {
            //TODO return error to login
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
