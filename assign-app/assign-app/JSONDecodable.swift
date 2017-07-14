//
//  JSONDecodable.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// Abstract class for the JSON init method.
protocol JSONDecodable {

    init?(JSON: [String: Any])

}
