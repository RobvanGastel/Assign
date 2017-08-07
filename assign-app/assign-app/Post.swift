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

    var id:Int!
    var title:String!
    var text:String!
    var url: String!
    var user:User?
    var tags:[String]?
    var done:Bool?
    var dateCreated:Date?
    var dateDone:Date?

    init(id:Int, title:String,  text:String, dateCreated:Date, url: String,
         user: User, done: Bool, tags: [String]) {
        self.id = id
        self.title = title
        self.text = text
        self.user = user
        self.dateCreated = dateCreated
        self.url = url
        self.done = done
        self.tags = tags
    }

    convenience required init?(JSON: [String: Any]) {
        // Post
        guard let id = JSON["id"] as? Int else { return nil }
        guard let title = JSON["title"] as? String else { return nil }
        guard let url = JSON["url"] as? String else { return nil }
        guard let text = JSON["description"] as? String else { return nil }
        guard let dateCreatedString = JSON["dateCreated"] as? String else { return nil }
        guard let done = JSON["done"] as? Bool else { return nil }
        let tags = JSON["tags"] as! [String]
        
        
        let dateCreated = JSONParser.dateFromString(dateString: dateCreatedString)
        let urlString = "http://84.26.134.115:8080/assign/#/" + url;
        
        // User
        let userString = JSON["user"] as! [String: Any]
        let user = User(JSON: userString)

        // Init the Post
        self.init(id: id, title: title, text: text, dateCreated: dateCreated, url: urlString, user: user!, done: done, tags: tags)
    }
}

