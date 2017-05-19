//
//  Storage.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

class Storage {
    
    class func getToken() -> String {
        let defaults = UserDefaults.standard
        return defaults.string(forKey: "token")!
    }
    
    class func getUser() -> User {
        let defaults = UserDefaults.standard
        return defaults.object(forKey: "currentUser")! as! User
    }
    
    class func getLoggedIn() -> Bool {
        let defaults = UserDefaults.standard
        return defaults.bool(forKey: "loggedIn")
    }
    
    class func getURL() -> String {
        return "http://84.26.134.115:8080/assign/api/"
    }
    
    class func setToken(token: String) {
        let defaults = UserDefaults.standard
        defaults.set(token, forKey: "token")
    }
    
    class func setUser(user: User) {
        let defaults = UserDefaults.standard
        defaults.set(user, forKey: "currentUser")
    }
    
    class func setLoggedIn(loggedIn: Bool) {
        let defaults = UserDefaults.standard
        defaults.set(loggedIn, forKey: "loggedIn")
    }
}
