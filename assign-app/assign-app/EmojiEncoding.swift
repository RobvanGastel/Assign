//
//  EmojiEncoding.swift
//  assign-app
//
//  Created by Rob Van Gastel on 01/11/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

extension String {
    func encode(_ s: String) -> String {
        let data = s.data(using: .nonLossyASCII, allowLossyConversion: true)!
        return String(data: data, encoding: .utf8)!
    }
    
    func decode(_ s: String) -> String? {
        let data = s.data(using: .utf8)!
        return String(data: data, encoding: .nonLossyASCII)
    }
}
