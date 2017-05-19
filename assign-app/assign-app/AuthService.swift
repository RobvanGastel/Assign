//
//  AuthService.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation
import Alamofire

class AuthService {
    //TODO create Authenticate
    //TODO create new Token
    
    //let sessionManager = SessionManager()
    //sessionManager.adapter
    
    func authenticate(email: String, password: String) -> Bool {
        let URL = Storage.getURL() + "/auth"
        
        let parameters: Parameters = [
            "email": email,
            "password": password
        ]
    
        Alamofire.request(URL, method: .post, parameters: parameters).validate().responseJSON { response in
            switch response.result {
            case .success:
                
                let json = response.result.value as? [String: Any]
                let Token = json?["id_token"] as? String
                
                Storage.setToken(token: Token!)
                print("Validation Successful accesToken:\(String(describing: Token))")
            case .failure(let error):
                print(error)
            }
        }
        return true
    }
    
    func refreshToken() -> Bool {
        return true
    }
    
    func register() -> Bool {
        return true
    }
}
