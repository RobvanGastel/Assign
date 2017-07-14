//
//  AuthService.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation
import Alamofire

/// Manages the Authentication API calls.
class AuthService {

    /// This function authenticates the Credentials against the API and sets
    /// the token in the defaults.
    func authenticate(email: String, password: String,
                      completionHandler: @escaping (Bool) -> ()) {
        let URL = Storage.getURL() + "/auth"

        let parameters: Parameters = [
            "email": email,
            "password": password
        ]

        Alamofire.request(URL, method: .post, parameters: parameters, encoding: URLEncoding.queryString).validate().responseJSON { response in
            switch response.result {
            case .success:

                let json = response.result.value as? [String: Any]
                let token = json?["id_token"] as? String

                // Set storage variables
                Storage.setToken(token: token!)
                Storage.setLoggedIn(loggedIn: false) // For debugging set to false
                Storage.setCredentials(credentials: Credentials(email: email, password: password))
                
                print("AUTH: Token request successful")

                completionHandler(true)
            case .failure(let error):
                print("AUTH: Token request error")
                print(error)
                completionHandler(false)
            }
        }
    }

    /// This function registers the User against the API.
    func register(email: String, password: String, code: String, name: String,
                  completionHandler: @escaping (Bool) -> ()) {
        
        let URL = Storage.getURL() + "/users"
        
        let parameters: Parameters = [
            "email": email,
            "password": password,
            "name": name,
            "code": code
        ]
        
        Alamofire.request(URL, method: .post, parameters: parameters, encoding: URLEncoding.queryString).validate().responseJSON { response in
            switch response.result {
            case .success:
                print("AUTH: Register request successful")
                completionHandler(true)
            case .failure(let error):
                print("AUTH: Register request error")
                print(error)
                completionHandler(false)
            }
        }
    }
}
