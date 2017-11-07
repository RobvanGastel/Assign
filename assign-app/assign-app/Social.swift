//
//  Social.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/07/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// TODO: Check if there are more variables in the backend.
/// The Social class of a User.
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
        
        var phonenumberString: String = ""
        var twitterString: String = ""
        var facebookString: String = ""
        
        if let phonenumber = JSON["phonenumber"] as? String {
            phonenumberString = phonenumber
        }
        
        if let twitter = JSON["twitter"] as? String {
            twitterString = twitter
        }
        
        if let facebook = JSON["facebook"] as? String {
            facebookString = facebook
        }
        
        self.init(phonenumber: phonenumberString, twitter: twitterString, facebook: facebookString)
    }
}
