//
//  JSONParser.swift
//  assign-app
//
//  Created by Rob Van Gastel on 30/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation


class JSONParser {
    
    class func dateFromString(dateString: String) -> Date {
        
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd-HH-mm"
        let dateObj = formatter.date(from: dateString)
        return dateObj!
    }
}
