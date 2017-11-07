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

    var id:Int!
    var name:String!
    var email:String!
    var profileImage: String?
    var posts:[Post]?
    var social: Social?
    var specialisation: String?
    var dateCreated:Date?


    // Post user initializer
    init(id: Int, name:String, email:String, dateCreated:Date, profileImage: String) {
        self.id = id
        self.name = name
        self.email = email
        self.profileImage = profileImage
        self.dateCreated = dateCreated
    }
    
    init(id: Int, name:String, email:String, dateCreated:Date, profileImage: String,
         social: Social, specialisation: String) {
        self.id = id
        self.name = name
        self.email = email
        self.profileImage = profileImage
        self.social = social
        self.specialisation = specialisation
        self.dateCreated = dateCreated
    }

    convenience required init?(JSON: [String: Any]) {
        guard let id = JSON["id"] as? Int else { return nil }
        guard let name = JSON["name"] as? String else { return nil }
        guard let email = JSON["email"] as? String else { return nil }
        guard let dateCreatedString = JSON["dateCreated"] as? String else { return nil }
        guard let profileImage = JSON["profileImage"] as? String else { return nil }
        
        let dateCreated = JSONParser.dateFromString(dateString: dateCreatedString)
        let profileImageString = Storage.getURL() + "/img/" + profileImage;
            
        let socialString = JSON["socialLink"] as? [String: Any]
        let socialLink = Social(JSON: socialString!)
        guard let specilisation = JSON["specialisation"] as? String else { return nil }
        
        self.init(id: id, name: String().decode(name)!, email: email, dateCreated: dateCreated, profileImage: profileImageString, social: socialLink!, specialisation: specilisation)
    }
}
