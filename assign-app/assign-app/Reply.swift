//
//  Reply.swift
//  assign-app
//
//  Created by Rob Van Gastel on 17/07/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// The Reply class between Post and User.
class Reply:NSObject, JSONDecodable {
    
    var id:Int!
    var post: Post!
    var user: User!
    var dateCreated: Date!
    var helped: Bool
    
    init(id: Int, post: Post, user: User, dateCreated: Date, helped: Bool) {
        self.id = id
        self.post = post
        self.user = user
        self.dateCreated = dateCreated
        self.helped = helped
    }
    
    convenience required init?(JSON: [String: Any]) {
        guard let id = JSON["id"] as? Int else { return nil }
        guard let dateCreatedString = JSON["dateCreated"] as? String else { return nil }
        guard let helped = JSON["helped"] as? Bool else { return nil }
        
        // Init User
        let userString = JSON["user"] as! [String: Any]
        let user = User(JSON: userString)
        
        // Init Post
        let postString = JSON["post"] as! [String: Any]
        let post = Post(JSON: postString)
        
        // Set dateCreated
        let dateCreated = JSONParser.dateFromString(dateString: dateCreatedString)
        
        self.init(id: id, post: post!, user: user!, dateCreated: dateCreated, helped: helped)
    }
}
