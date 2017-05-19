//
//  ApiService.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

//Add sessionManager
//Add RequestRetrier, RequestAdapter (for adding Headers)

class ApiService {
    
    let sessionManager: SessionManager
    
    init() {
        let jwtHandler = JWTHandler()
        sessionManager = SessionManager()
        sessionManager.adapter = jwtHandler
        sessionManager.retrier = jwtHandler
    }
   
//TODO Add completion handlers
//http://stackoverflow.com/questions/29852431/alamofire-asynchronous-completionhandler-for-json-request
//
//    func getCurrentUser() -> User {
//        let URL = Storage.getURL() + "/users/email"
//        var user
//        
//        let parameters: Parameters = [
//            "email": Storage.getCredentials().email!
//        ]
//        
//        sessionManager.request(URL, method: .get, parameters: parameters, encoding: URLEncoding.queryString).validate().responseJSON { response in
//            switch response.result {
//            case .success:
//                //TODO add serializer for User
//                
//                let userResponse = response.map { json in
//                    // We assume an existing User(json: Any) initializer
//                    return User(JSON: json)
//                }
//                
//                print("API: Retrieve user successful")
//                
//            case .failure(let error):
//                print(error)
//            }
//        }
//        return nil
//    }
    
//    func getPosts() -> Post {
//        let URL = Storage.getURL() + "/posts"
//        let user = Post(id: <#Int#>, title: <#String#>, user: <#String#>, date: <#String#>, profile: <#String#>)
//        
//        sessionManager.request(URL, method: .get).validate().responseJSON { response in
//            switch response.result {
//            case .success:
//                //TODO add serializer for Posts
//                print("API: Retrieve posts successful")
//                
//            case .failure(let error):
//                print(error)
//            }
//        }
//        return user
//    }
}
