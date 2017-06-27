//
//  User.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/01/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// The User class
class User:NSObject, JSONDecodable {

    var id:Int?
    var firstName:String?
    var email:String?
    var user: User?
    var posts:[Post]?
    var dateCreated:Date?

    init(id: Int, firstName:String, email:String) {
        self.id = id;
        self.firstName = firstName;
        self.email = email;
    }

    init(id: Int, firstName:String, user: User, email:String, dateCreated:Date) {
        self.id = id;
        self.firstName = firstName;
        self.email = email;
        self.user = user;
        self.dateCreated = dateCreated;
    }

    convenience required init?(JSON: [String: Any]) {
        guard let id = JSON["id"] as? Int else { return nil }
        guard let firstName = JSON["firstName"] as? String else { return nil }
        guard let email = JSON["email"] as? String else { return nil }
        //TODO add serializer for Date
        //guard let dateCreated = JSON["dateCreated"] as? Double else { return nil }

        self.init(id: id, firstName: firstName, email: email)
    }
}
