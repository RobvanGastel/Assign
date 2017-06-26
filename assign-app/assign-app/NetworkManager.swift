//
//  NetworkManager.swift
//  assign-app
//
//  Created by Rob Van Gastel on 21/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation
import Alamofire

/// Manages the Singleton of the SessionManager for Alamofire.
class NetworkManager {
    
    private static var manager: SessionManager = {
        var manager: SessionManager
        
        let jwtHandler = JWTHandler()
        manager = SessionManager()
        manager.adapter = jwtHandler
        manager.retrier = jwtHandler
        
        return manager;
    }()
    
    class func shared() -> SessionManager {
        return manager
    }
}
