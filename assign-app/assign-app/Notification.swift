0//
//  Notification.swift
//  assign-app
//
//  Created by Rob Van Gastel on 30/08/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// The User class
class Notification:NSObject, JSONDecodable {
    
    var id:Int!
    var title:String!
    var body:String?
    var dateCreated: Date!
    
    init(id:Int, title:String,  body:String, dateCreated:Date) {
        self.id = id
        self.title = title
        self.body = body
        self.dateCreated = dateCreated
    }
    
    convenience required init?(JSON: [String: Any]) {
        guard let id = JSON["id"] as? Int else { return nil }
        guard let title = JSON["title"] as? String else { return nil }
        guard let body = JSON["body"] as? String else { return nil }
        
        guard let dateCreatedString = JSON["dateCreated"] as? String else { return nil }
        
        let dateCreated = JSONParser.dateFromString(dateString: dateCreatedString)
        
        // Init the Notification
        self.init(id: id, title: title, body: body, dateCreated: dateCreated)
    }
}
