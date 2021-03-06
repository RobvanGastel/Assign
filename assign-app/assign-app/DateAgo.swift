//
//  DateAgo.swift
//  assign-app
//
//  Created by Rob Van Gastel on 28/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// Date extension to get the time ago string.
/// TODO: localize for other calanders
extension Date {
    
    // shows 1 or two letter abbreviation for units.
    // does not include 'ago' text ... just {value}{unit-abbreviation}
    public var timeAgoSimple: String {
        let components = self.dateComponents()
        
        if components.year! > 0 {
            return self.string(fromFormat: "%%djr", withValue: components.year!)
//            return self.string(fromFormat: "%%dyr", withValue: components.year!)
        }
        
        if components.month! > 0 {
            return self.string(fromFormat: "%%dmd", withValue: components.month!)
//            return self.string(fromFormat: "%%dmo", withValue: components.month!)
        }
        
        // TODO: localize for other calanders
        if components.day! >= 7 {
            let value = components.day!/7
            return self.string(fromFormat: "%%dw", withValue: value)
        }
        
        if components.day! > 0 {
            return self.string(fromFormat: "%%dd", withValue: components.day!)
        }
        
        if components.hour! > 0 {
            return self.string(fromFormat: "%%du", withValue: components.hour!)
//            return self.string(fromFormat: "%%dh", withValue: components.hour!)
        }
        
        if components.minute! > 0 {
            return self.string(fromFormat: "%%dm", withValue: components.minute!)
        }
        
        if components.second! > 0 {
            return self.string(fromFormat: "%%ds", withValue: components.second! )
        }
        
        return ""
    }
    
    public var timeAgo: String {
        let components = self.dateComponents()

        if components.year! > 0 {
            if components.year! < 2 {
                return "Vorig jaar"
//                return "Last year"
            } else {
                return self.string(fromFormat: "%%d jaar geleden", withValue: components.year!)
//                return self.string(fromFormat: "%%d years ago", withValue: components.year!)
            }
        }

        if components.month! > 0 {
            if components.month! < 2 {
                return "Vorige maand"
//                return "Last month"
            } else {
                return self.string(fromFormat: "%%d maanden geleden!", withValue: components.month!)
//                return self.string(fromFormat: "%%d months ago", withValue: components.month!)
            }
        }

        if components.day! >= 7 {
            let week = components.day!/7
            if week < 2 {
                return "Vorige week"
//                return "Last week"
            } else {
                return self.string(fromFormat: "%%d weken geleden", withValue: week)
//                return self.string(fromFormat: "%%d weeks ago", withValue: week)
            }
        }

        if components.day! > 0 {
            if components.day! < 2 {
                return "Gisteren"
//                return "Yesterday"
            } else  {
                return self.string(fromFormat: "%%d dagen geleden", withValue: components.day!)
//                return self.string(fromFormat: "%%d days ago", withValue: components.day!)
            }
        }

        if components.hour! > 0 {
            if components.hour! < 2 {
                return "Een uur geleden"
//                return "An hour ago"
            } else  {
                return self.string(fromFormat: "%%d uur geleden", withValue: components.hour!)
//                return self.string(fromFormat: "%%d hours ago", withValue: components.hour!)
            }
        }

        if components.minute! > 0 {
            if components.minute! < 2 {
                return "Een minuut geleden"
//                return "A minute ago"
            } else {
                return self.string(fromFormat: "%%d minuten geleden", withValue: components.minute!)
//                return self.string(fromFormat: "%%d minutes ago", withValue: components.minute!)
            }
        }

        if components.second! > 0 {
            if components.second! < 5 {
                return "Zo net"
//                return "Just now"
            } else {
                return self.string(fromFormat: "%%d seconden geleden", withValue: components.second!)
//                return self.string(fromFormat: "%%d seconds ago", withValue: components.second!)
            }
        }

        return ""
    }
    
    fileprivate func dateComponents() -> DateComponents {
        return Calendar.current.dateComponents([.second, .minute, .hour, .day, .month, .year], from: self, to: Date())
    }
    
    fileprivate func string(fromFormat format: String, withValue value: Int) -> String {
        let localeFormat = String(format: format)
        return String(format: NSDateTimeAgoLocalizedStrings(localeFormat), value)
    }
    
    fileprivate func NSDateTimeAgoLocalizedStrings(_ key: String) -> String {
        return key
    }
}
