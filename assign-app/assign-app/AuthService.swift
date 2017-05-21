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
    
    func authenticate(email: String, password: String,
                      completionHandler:@escaping (Bool) -> ()) {
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
                
                Storage.setToken(token: token!)
                print("AUTH: Token request successful")
                
                completionHandler(true)
            case .failure(let error):
                print("AUTH: Token request error")
                print(error)
                completionHandler(false)
            }
        }
    }
    
    //TODO implement method
    func register() -> Bool {
        return true
    }
}
