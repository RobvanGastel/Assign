//
//  Reply.swift
//  assign-app
//
//  Created by Rob Van Gastel on 17/07/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// The
class Reply:NSObject, JSONDecodable {
    
    var id:Int!
    
    
    init(id: Int) {
        self.id = id
    }
    
    convenience required init?(JSON: [String: Any]) {
        guard let id = JSON["id"] as? Int else { return nil }
        
        self.init(id: id)
    }
}
