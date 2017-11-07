//
//  EmojiEncoding.swift
//  assign-app
//
//  Created by Rob Van Gastel on 01/11/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

extension String {
    
    // Encode string to include emoji's for backend
    func encode(_ s: String) -> String {
        let data = s.data(using: .nonLossyASCII, allowLossyConversion: true)!
        return String(data: data, encoding: .utf8)!
    }
    
    // Decode string when received from backend to include emoji's
    func decode(_ s: String) -> String? {
        let data = s.data(using: .utf8)!
        return String(data: data, encoding: .nonLossyASCII)
    }
}
