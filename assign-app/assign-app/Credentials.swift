//
//  Credentials.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

class Credentials {
    
    var email:String?
    var password:String?

    init(email:String, password:String) {
        self.email = email
        self.password = password
    }
}
