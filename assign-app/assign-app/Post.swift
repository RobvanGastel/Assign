//
//  Assignment.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/01/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

public struct Post {

    var id:Int?
    var title:String?
    var user:String?
    var description:String?
    var dateCreated:Date?
    var date:String?
    
    init(id:Int, title:String, user:String, date:String) {
        self.id = id
        self.title = title
        self.user = user
        self.date = date
    }
}

extension Post: JSONDecodable {
    
     public init(decoder: JSONDecoder) throws {
        //guard let dateCreated = JSON["dateCreated"] as? String else { return nil }
        
        //Assign values from JSON
        self.id = try decoder.decode(key: "id")
        self.title = try decoder.decode(key: "title")
        self.description = try decoder.decode(key: "description")
        
    }
}
