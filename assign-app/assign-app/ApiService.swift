//
//  ApiService.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// Manages the API calls.
class ApiService {

    /// This function returns the *User* object of the authenticated user.
    @discardableResult
    func getCurrentUser(completionHandler: @escaping (_ response: User?) -> Void) -> Alamofire.DataRequest {
        let sessionManager = NetworkManager.shared()
        
        let URL = Storage.getURL() + "/users/email"
        
        let parameters: Parameters = [
            "email": Storage.getCredentials().email!
        ]
        
        return sessionManager.request(URL, method: .get, parameters: parameters,
                                      encoding: URLEncoding.queryString).validate().responseJSON{ response in
            
        switch response.result {
            case .success:
                let user = User(JSON: response.value as! [String : Any])

                print("API: Retrieve user successful")
                completionHandler(user)
            
            case .failure(let error):
                print("API: Retrieve user error")
                print(error)
            }
       }
    }
    
    /// This function returns a list of *Post* objects for the authenticated user.
    @discardableResult
    func getPosts() -> Post {
        let URL = Storage.getURL() + "/posts"
        let user = Post(id: Int, title: String, user: String, date: String, profile: String)
        
        sessionManager.request(URL, method: .get).validate().responseJSON { response in
            switch response.result {
            case .success:
                //TODO add serializer for Posts
                print("API: Retrieve posts successful")
                
            case .failure(let error):
                print(error)
            }
        }
        return user
    }
}
