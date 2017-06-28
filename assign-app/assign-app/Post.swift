//
//  Assignment.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/01/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// The Post class for the posts of users.
class Post:NSObject, JSONDecodable {

    var id:Int?
    var title:String?
    var text:String?
    
    var user:User?
    var tags:[Tag]?
    var done:Bool?

    var profile: String?
    var dateCreated:Date?
    var dateDone:Date?

    init(id:Int, title:String, text:String, dateCreated:Date, user: User) {
        self.id = id
        self.title = title
        self.text = text
        self.dateCreated = dateCreated
        self.user = user
    }

    init(id:Int, title:String, user: String, dateCreated:Date, text:String, profile: String) {
        self.id = id
        self.title = title
        self.text = text
        self.profile = profile
        self.dateCreated = dateCreated
    }

    convenience required init?(JSON: [String: Any]) {
        // Post
        guard let id = JSON["id"] as? Int else { return nil }
        guard let title = JSON["title"] as? String else { return nil }
        guard let text = JSON["description"] as? String else { return nil }
        guard let dateCreatedString = JSON["dateCreated"] as? String else { return nil }
        
        // User
        let userString = JSON["user"] as! [String: Any]
        let user = User(JSON: userString)
        
        // Format the Java date 
        // TODO add as extension to Date
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd-HH-mm"
        let dateCreated = formatter.date(from: dateCreatedString)
        
        // Init the Post
        self.init(id: id, title: title, text: text, dateCreated: dateCreated!, user: user!)
    }
}

