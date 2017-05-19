//
//  User.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/01/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

class User: JSONDecodable {
    
    var id:Int?
    var firstName:String?
    var email:String?
    var posts:[Post]?
    var dateCreated:Date?
    
    init(id: Int, firstName:String, email:String, dateCreated:Date) {
        self.id = id;
        self.firstName = firstName;
        self.email = email;
        self.dateCreated = dateCreated;
    }
    
    public required init?(JSON: Any) {
        guard let JSON = JSON as? [String: AnyObject] else { return nil }
        
        guard let id = JSON["id"] as? Int else { return nil }
        guard let firstName = JSON["firstName"] as? String else { return nil }
        guard let email = JSON["email"] as? String else { return nil }
        
        self.id = id;
        self.firstName = firstName;
        self.email = email;
    }
}
