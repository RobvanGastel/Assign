//
//  RefreshPostsDelegate.swift
//  assign-app
//
//  Created by Rob Van Gastel on 08/11/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

protocol RefreshPostsDelegate: class {
    
    /// Refreshes the view in the controller.
    func refreshPosts()
}
