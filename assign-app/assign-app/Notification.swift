//
//  Notification.swift
//  assign-app
//
//  Created by Rob Van Gastel on 30/08/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// The Notification class
class Notification:NSObject, JSONDecodable {
    
    var id:Int!
    var title:String!
    var body:String?
    var postId:Int?
    var sender: User!
    var readNotification:Bool!
    var dateCreated: Date!
    
    init(id:Int, title:String,  body:String, sender: User, readNotification:Bool, postId:Int, dateCreated:Date) {
        self.id = id
        self.title = title
        self.body = body
        self.postId = postId
        self.readNotification = readNotification
        self.sender = sender
        self.dateCreated = dateCreated
    }
    
    convenience required init?(JSON: [String: Any]) {
        guard let id = JSON["id"] as? Int else { return nil }
        guard let title = JSON["title"] as? String else { return nil }
        guard let body = JSON["body"] as? String else { return nil }
        guard let readNotification = JSON["readNotification"] as? Bool else { return nil }
        guard let postId = JSON["postId"] as? Int else { return nil }
        
        // Set DateCreated
        guard let dateCreatedString = JSON["dateCreated"] as? String else { return nil }
        let dateCreated = JSONParser.dateFromString(dateString: dateCreatedString)
        
        // Init User
        let userString = JSON["sender"] as! [String: Any]
        let user = User(JSON: userString)
        
        self.init(id: id, title: String().decode(title)!, body: String().decode(body)!, sender: user!, readNotification: readNotification, postId: postId, dateCreated: dateCreated)
    }
}
