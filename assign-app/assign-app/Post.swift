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
    var user:String?
    var dateCreated:Date?
    var date:String?
    
    init(id:Int, title:String, user:String, date:String) {
        self.id = id
        self.title = title
        self.user = user
        self.date = date
    }
}
