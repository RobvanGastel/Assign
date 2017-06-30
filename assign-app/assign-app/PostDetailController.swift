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
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    
    // Provided data from the segue
    var currentPost:Post?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Set the data to the labels in the view
        self.userLabel.text = currentPost?.title
        self.titleLabel.text = currentPost?.text
        self.nameLabel.text = currentPost?.user?.name
        self.dateLabel.text = currentPost?.dateCreated?.timeAgo
    
        let tap = UITapGestureRecognizer(target: self, action: #selector(PostDetailController.tapFunction))
        nameLabel.isUserInteractionEnabled = true
        nameLabel.addGestureRecognizer(tap)
    }

    func tapFunction(sender:UITapGestureRecognizer) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: "ProfileDetailController") as! ProfileDetailController
        vc.currentUser = currentPost?.user
        self.present(vc, animated: false, completion: nil)
    }
}

