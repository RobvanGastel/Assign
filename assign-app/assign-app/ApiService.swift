//
//  ApiService.swift
//  assign-app
//
//  Created by Rob Van Gastel on 05/02/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

// MARK: - ApiService Errors
enum ApiServiceError: Error {
    
    case Unknown
    case FailedRequest
    case InvalidResponse
    
}

final class ApiService {
    
    typealias getPostsCompletion = (AnyObject?, ApiServiceError?) -> ()
    
    //Base URL for all API Requests
    let baseURL = "http:/localhost:8084/api/"
    //Future credentials to identify User in the back-end
    let credentials = "Not implemented"
    
    //	MARK: - Request Posts
    func getPosts(completion: @escaping getPostsCompletion) {
        
        // Create URL
        //let URL = baseURL.appendingPathComponent("\(id))
        let postURL = URL(string: baseURL + "/posts/")
        
        // Create Data Task
        URLSession.shared.dataTask(with: postURL!) { (data, response, error) in
            self.didFetchPosts(data: data, response: response, error: error, completion: completion)
            }.resume()
    }
    
    //	MARK: - Process Posts
    private func didFetchPosts(data: Data?, response: URLResponse?, error: Error?, completion: getPostsCompletion) {
        //Error Check on request
        if let _ = error {
            completion(nil, .FailedRequest)
            
        } else if let data = data, let response = response as? HTTPURLResponse {
            //Check if valid response status code
            if response.statusCode == 200 {
                
                //Process JSON
                if let JSON = try? JSONSerialization.jsonObject(with: data, options: []) as AnyObject {
                    completion(JSON, nil)
                } else {
                    completion(nil, .InvalidResponse)
                }
            } else {
                completion(nil, .FailedRequest)
            }
            
        } else {
            completion(nil, .Unknown)
        }
    }
}
