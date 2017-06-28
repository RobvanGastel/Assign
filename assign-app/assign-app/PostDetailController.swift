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
    @IBOutlet weak var dateLabel: UIName!
    @IBOutlet weak var nameLabel: UITitle!
    
    // Provided data from the segue
    var currentPost:Post?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Set the data to the labels in the view
        self.userLabel.text = currentPost?.title
        self.titleLabel.text = currentPost?.text
        self.nameLabel.text = currentPost?.user?.firstName
        self.dateLabel.text = currentPost?.dateCreated?.timeAgo
    }
}
