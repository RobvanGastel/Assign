//
//  JSONParser.swift
//  assign-app
//
//  Created by Rob Van Gastel on 30/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// JSON Parser class to format JSON
class JSONParser {
    
    /// Format the SimpleDateFormat to Swift Date.
    class func dateFromString(dateString: String) -> Date {
        
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd-HH-mm"
        let dateObj = formatter.date(from: dateString)
        return dateObj!
    }
}
