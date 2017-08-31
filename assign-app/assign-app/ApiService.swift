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
                Storage.setUser(user: user!)
                
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
    func getPosts(size: Int, start: Int,
                  completionHandler: @escaping (_ response: [Post]?) -> Void) -> Alamofire.DataRequest {
        let sessionManager = NetworkManager.shared()

        let URL = Storage.getURL() + "/posts"
        
        let parameters: Parameters = [
            "size" : size,
            "start" : start
        ]

        return sessionManager.request(URL, method: .get, parameters: parameters,
                                      encoding: URLEncoding.queryString).validate().responseJSON{ response in

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
            .validate(statusCode: 200..<300).responseData { response in
                
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

    /// This function searches through the posts and returns the matching 
    /// posts.
    @discardableResult
    func searchPosts(size: Int, start: Int, query: String,
                     completionHandler: @escaping (_ response: [Post]?) -> Void) -> Alamofire.DataRequest {
        let sessionManager = NetworkManager.shared()
        
        let parameters: Parameters = [
            "query": query,
            "size" : size,
            "start" : start
        ]
        
        let URL = Storage.getURL() + "/posts/query"
        
        return sessionManager.request(URL, method: .get, parameters: parameters,
                                      encoding: URLEncoding.queryString)
            .validate().responseJSON{ response in
                
                switch response.result {
                case .success:
                    var postsArray = [Post]()
                    
                    if let posts = response.value as? [[String:Any]] {
                        for post in posts {
                            postsArray.append(Post(JSON: post)!)
                        }
                    }
                    
                    print("API: Retrieve search posts successful")
                    completionHandler(postsArray)
                    
                case .failure(let error):
                    print("API: Retrieve search posts error")
                    print(error)
                }
        }
    }
    
    /// This function returns a list of *Post* objects for the authenticated user.
    @discardableResult
    func getPostsByUser(size: Int, start: Int, id: Int,
                  completionHandler: @escaping (_ response: [Post]?) -> Void) -> Alamofire.DataRequest {
        let sessionManager = NetworkManager.shared()
        
        let idString = "\(id)"
        let URL = Storage.getURL() + "/users/" + idString + "/posts"
        
        let parameters: Parameters = [
            "size" : size,
            "start" : start
        ]
        
        return sessionManager.request(URL, method: .get, parameters: parameters,
            encoding: URLEncoding.queryString).validate().responseJSON{ response in
                                        
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
    
    /// This function returns a list of *Reply* objects for the user by id.
    @discardableResult
    func getRepliesByUser(size: Int, start: Int, id: Int,
                        completionHandler: @escaping (_ response: [Reply]?) -> Void) -> Alamofire.DataRequest {
        let sessionManager = NetworkManager.shared()
        
        let idString = "\(id)"
        let URL = Storage.getURL() + "/users/" + idString + "/replies"
        
        let parameters: Parameters = [
            "size" : size,
            "start" : start
        ]
        
        return sessionManager.request(URL, method: .get, parameters: parameters,
                                      encoding: URLEncoding.queryString).validate()
            .responseJSON{ response in
                                        
            switch response.result {
            case .success:
                var replyArray = [Reply]()
                
                if let replies = response.value as? [[String:Any]] {
                    for reply in replies {
                        replyArray.append(Reply(JSON: reply)!)
                    }
                }
                
                print("API: Retrieve replies successful")
                completionHandler(replyArray)
                
            case .failure(let error):
                print("API: Retrieve replies error")
                print(error)
            }
        }
    }
    
    /// This function creates a Reply for given Post and User.
    @discardableResult
    func addReply(id: Int,
                  completionHandler: @escaping (_ response: Bool) -> Void) -> Alamofire.DataRequest {
        let sessionManager = NetworkManager.shared()
        
        let postId = "\(id)"
        let URL = Storage.getURL() + "/posts/" + postId + "/replies"
        
        return sessionManager.request(URL, method: .get,
                                      encoding: URLEncoding.queryString).validate()
        .responseJSON { response in
                
            switch response.result {
                
            case .success:
                print("API: Create reply successful")
                completionHandler(true)
                
            case .failure(let error):
                print("API: Create reply error")
                completionHandler(false)
                print(error)
            }
        }
    }
    
    /// This function checks if the user has already replied to this Post.
    @discardableResult
    func checkReplied(id: Int,
                  completionHandler: @escaping (_ response: Bool) -> Void) -> Alamofire.DataRequest {
        let sessionManager = NetworkManager.shared()
        
        let postId = "\(id)"
        let URL = Storage.getURL() + "/posts/" + postId + "/replied"
        
        return sessionManager.request(URL, method: .get,
                                      encoding: URLEncoding.queryString).validate()
            .responseJSON { response in
                
                switch response.result {
                    
                case .success:
                    
                    if let JSON = response.value as? [String:Any] {
                        
                        if let replied = JSON["replied"] as? Bool {
                            print("API: Create reply successful")
                            completionHandler(replied)
                        }
                    }
                    
                    completionHandler(false)
                    
                case .failure(let error):
                    print("API: Create reply error")
                    completionHandler(false)
                    print(error)
                }
        }
    }
    
    /// This function registerd the Firebase token in the API.
    @discardableResult
    func registerDevice(token: String,
                        completionHandler: @escaping (_ response: Bool) -> Void) -> Alamofire.DataRequest {
        let sessionManager = NetworkManager.shared()
        
        let URL = Storage.getURL() + "/firebase"
        
        let parameters: Parameters = [
            "id" : token
        ]
        
        return sessionManager.request(URL, method: .post, parameters: parameters,
                                      encoding: URLEncoding.queryString).validate()
            .responseJSON{ response in
                                        
                switch response.result {
                case .success:
                    completionHandler(true)
                    
                case .failure(let error):
                    print("API: register token error")
                    completionHandler(false)
                    print(error)
                }
        }
    }
    
    /// This function returns a list of *Notification* objects for the authenticated user.
    @discardableResult
    func getNotifications(size: Int, start: Int,
                  completionHandler: @escaping (_ response: [Notification]?) -> Void) -> Alamofire.DataRequest {
        let sessionManager = NetworkManager.shared()
        
        let URL = Storage.getURL() + "/notifications"
        
        let parameters: Parameters = [
            "size" : size,
            "start" : start
        ]
        
        return sessionManager.request(URL, method: .get, parameters: parameters,
                                      encoding: URLEncoding.queryString).validate().responseJSON{ response in
                                        
            switch response.result {
            case .success:
                var notificationsArray = [Notification]()
                
                if let notifications = response.value as? [[String:Any]] {
                    for notification in notifications {
                        notificationsArray.append(Notification(JSON: notification)!)
                    }
                }
                
                print("API: Retrieve posts successful")
                completionHandler(notificationsArray)
                
            case .failure(let error):
                print("API: Retrieve posts error")
                print(error)
            }
        }
    }
}
