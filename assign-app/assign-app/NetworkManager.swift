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

    /// Singleton Initialization
    private static var manager: SessionManager = {
        var manager: SessionManager

        // Init the JWT handler and Session Manager
        let jwtHandler = JWTHandler()
        manager = SessionManager()
        manager.adapter = jwtHandler
        manager.retrier = jwtHandler

        return manager;
    }()

    /// Getter for the Singleton SessionManager.
    class func shared() -> SessionManager {
        return manager
    }
}
