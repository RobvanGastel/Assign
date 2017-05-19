//
//  Assignment.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/01/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

class Post: JSONDecodable {

    var id:Int?
    var title:String?
    var description:String?
    
    var user:User?
    var tags:[Tag]?
    var done:Bool?
    
    var dateCreated:Date?
    var dateDone:Date?
    
    init(id:Int, title:String, user:User, dateCreated:Date, description:String) {
        self.id = id
        self.title = title
        self.description = description
        self.user = user
        self.dateCreated = dateCreated
    }
    
    public required init?(JSON: Any) {
        guard let JSON = JSON as? [String: AnyObject] else { return nil }
        
        guard let id = JSON["id"] as? Int else { return nil }
        guard let title = JSON["title"] as? String else { return nil }
        guard let description = JSON["description"] as? String else { return nil }
        guard let dateCreated = JSON["dateCreated"] as? Double else { return nil }
        
        self.id = id
        self.title = title
        self.description = description
        self.dateCreated = Date(timeIntervalSince1970: dateCreated)
    }
}
