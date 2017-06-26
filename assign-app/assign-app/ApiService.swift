//
//  ApiService.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// Manages the API calls.
///
/// TODO Add check if user is connected to Network.
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
    ///
    /// TODO Retrieve custom Posts of Call
    @discardableResult
    func getPosts(completionHandler: @escaping (_ response: [Post]?) -> Void) -> Alamofire.DataRequest {
        let sessionManager = NetworkManager.shared()
        
        let URL = Storage.getURL() + "/posts"

        return sessionManager.request(URL, method: .get, encoding: URLEncoding.queryString)
                                    .validate().responseJSON{ response in
                
            switch response.result {
            case .success:
                var postsArray = [Post]()
                
                if let posts = response.value as? [[String:Any]] {
                    for post in posts {
                        postsArray.append(Post(JSON: post)!)
                    }
                }
                
                print("API: Retrieve posts successful")
                completionHandler(postsArray)
                
            case .failure(let error):
                print("API: Retrieve posts error")
                print(error)
            }
        }
    }
    
    /// This function posts a *Post* for the authenticated user.
    @discardableResult
    func addPost(title: String, description: String,
                 completionHandler: @escaping (Bool) -> ()) -> Alamofire.DataRequest {
        let sessionManager = NetworkManager.shared()
        
            let parameters: Parameters = [
                "title": title,
                "description": description
            ]
        
        let URL = Storage.getURL() + "/posts"
        
        return sessionManager.request(URL, method: .post, parameters: parameters,
                                      encoding: URLEncoding.queryString)
            .validate().responseJSON{ response in
                
                switch response.result {
                case .success:
                    
                    print("API: Post sent successful")
                    completionHandler(true)
                    
                case .failure(let error):
                    print("API: Post sent error")
                    completionHandler(false)
                    print(error)
                }
        }
    }
}
