//
//  Credentials.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// This object is to manage the Credentials
class Credentials:NSObject  {

    var email:String?
    var password:String?

    init(email:String, password:String) {
        self.email = email
        self.password = password
    }
}
