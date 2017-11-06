//
//  Item.swift
//  assign-app
//
//  Created by Rob Van Gastel on 04/11/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// The Item class
class Item:NSObject, JSONDecodable {
    
    var id:Int!
    var user: User!
    var title:String!
    var text:String!
    var dateCreated:Date?
    
    init(id: Int, user: User, title: String, text: String, dateCreated: Date) {
        self.id = id
        self.user = user
        self.title = title
        self.text = text
        self.dateCreated = dateCreated
    }
    
    convenience required init?(JSON: [String: Any]) {
        guard let id = JSON["id"] as? Int else { return nil }
        guard let title = JSON["title"] as? String else { return nil }
        guard let text = JSON["description"] as? String else { return nil }
        guard let dateCreatedString = JSON["dateCreated"] as? String else { return nil }
        
        // User
        let userString = JSON["user"] as! [String: Any]
        let user = User(JSON: userString)
        
        let dateCreated = JSONParser.dateFromString(dateString: dateCreatedString)

        // Init the item
        self.init(id: id, user: user!, title: String().decode(title)!, text: String().decode(text)!, dateCreated: dateCreated)
    }
}
