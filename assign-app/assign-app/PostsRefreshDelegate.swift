//
//  PostsRefreshDelegate.swift
//  assign-app
//
//  Created by Rob Van Gastel on 03/07/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

protocol PostsRefreshDelegate: class {
    
    /// Refreshes the posts in the controller.
    func refreshPosts()
    
}
