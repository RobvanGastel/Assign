//
//  Assignment.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/01/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

class Post {

    var id:Int?
    var title:String?
    var description:String?
    var dateCreated:Date?
    var date:String?
    
    init(id:Int, title:String, description:String, date:String) {
        self.id = id
        self.title = title
        self.description = description
        self.date = date
    }
}
