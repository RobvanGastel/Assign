//
//  PostDetailController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 26/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

/// Controller to view the details of a post.
class PostDetailController: UIViewController {

    @IBOutlet weak var userLabel: UILabel!
    @IBOutlet weak var titleLabel: UILabel!
    
    var currentPost:Post?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.userLabel.text = currentPost?.title
        self.titleLabel.text = currentPost?.text

        // Do any additional setup after loading the view.
    }
}
