//
//  Storage.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// Manages UserDefault variables with static methods.
class Storage {

    /// This function returns the token of this authenticated user.
    class func getToken() -> String {
        let defaults = UserDefaults.standard
        return defaults.string(forKey: "token")!
    }

    /// This function returns the *User* object of this authenticated user.
    class func getUser() -> User {
        let defaults = UserDefaults.standard
        let user = User(id: defaults.integer(forKey: "user_id"),
                        name: defaults.string(forKey: "user_name")!,
                        email: defaults.string(forKey: "user_email")!,
                        dateCreated: defaults.object(forKey: "user_dateCreated") as! Date,
                        profileImage: defaults.string(forKey: "user_profileImage")!)
        return user
    }

    /// This function returns this *Credentials* object of this authenticated user.
    class func getCredentials() -> Credentials {
        let defaults = UserDefaults.standard
        let credentials = Credentials(email: defaults.string(forKey: "credentials_email")!,
                                      password: defaults.string(forKey: "credentials_password")!)

        return credentials
    }

    /// This function returns the *Bool* logged in status of this user.
    class func getLoggedIn() -> Bool {
        let defaults = UserDefaults.standard
        return defaults.bool(forKey: "loggedIn")
    }

    /// This function returns the API url.
    class func getURL() -> String {
        return "http://84.26.134.115:8080/assign/api"
    }

    /// This function returns the *User* object of this authenticated user.
    class func setToken(token: String) {
        let defaults = UserDefaults.standard
        defaults.set(token, forKey: "token")
    }

    /// This function sets the *User* object of this authenticated user.
    class func setUser(user: User) {
        let defaults = UserDefaults.standard
        defaults.set(user.id, forKey:"user_id")
        defaults.set(user.email, forKey:"user_email")
        defaults.set(user.name, forKey:"user_name")
        defaults.set(user.profileImage, forKey:"user_profileImage")
        defaults.set(user.dateCreated, forKey:"user_dateCreated")
    }

    /// This function sets this *Credentials* object of this authenticated user.
    class func setCredentials(credentials: Credentials) {
        let defaults = UserDefaults.standard
        defaults.set(credentials.email, forKey:"credentials_email")
        defaults.set(credentials.password, forKey:"credentials_password")
    }

    /// This function sets the *Bool* logged in status of this user.
    class func setLoggedIn(loggedIn: Bool) {
        let defaults = UserDefaults.standard
        defaults.set(loggedIn, forKey: "loggedIn")
    }
}
