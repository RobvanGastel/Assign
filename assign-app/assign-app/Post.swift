//
//  Assignment.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/01/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// The Post class for the posts of users.
// TODO: Set url in backend or Storage
class Post:NSObject, JSONDecodable {

    var id:Int!
    var title:String!
    var text:String! // Description of the post
    var url: String! // Share url of the post
    var user:User?
    var tags:[String]?
    var replies: [Int]?
    var done:Bool?
    var dateCreated:Date?
    var dateDone:Date? // Date the post was set to done

    init(id:Int, title:String,  text:String, dateCreated:Date, url: String,
         user: User, done: Bool, tags: [String], replies: [Int]) {
        self.id = id
        self.title = title
        self.text = text
        self.user = user
        self.dateCreated = dateCreated
        self.url = url
        self.done = done
        self.tags = tags
        self.replies = replies
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
        let replies = JSON["replies"] as! [Int]
        
        // Set dateCreated
        let dateCreated = JSONParser.dateFromString(dateString: dateCreatedString)
        
        // TODO: Set url in backend or Storage
        // Url of the post
        let urlString = "https://assignapp.nl/" + url;
        
        // Init the User
        let userString = JSON["user"] as! [String: Any]
        let user = User(JSON: userString)

        // Init the Post
        self.init(id: id, title: String().decode(title)!, text: String().decode(text)!, dateCreated: dateCreated, url: urlString, user: user!, done: done, tags: tags, replies: replies)
    }
}

