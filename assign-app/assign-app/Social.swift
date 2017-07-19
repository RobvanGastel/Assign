//
//  Social.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/07/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

class Social:NSObject, JSONDecodable {
    
    var phonenumber: String?
    var twitter: String?
    var facebook: String?
    
    init(phonenumber: String, twitter: String, facebook: String) {
        self.phonenumber = phonenumber
        self.twitter = twitter
        self.facebook = facebook
    }
    
    convenience required init?(JSON: [String: Any]) {
        guard let phonenumber = JSON["phonenumber"] as? String else { return nil }
        guard let twitter = JSON["twitter"] as? String else { return nil }
        guard let facebook = JSON["facebook"] as? String else { return nil }
        
        self.init(phonenumber: phonenumber, twitter: twitter, facebook: facebook)
    }
}
