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
    @IBOutlet weak var nameButton: UIButton!
    @IBOutlet weak var profileImage: UIImageView!
    
    // Provided data from the segue
    var currentPost:Post?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Set the data to the labels in the view
        self.userLabel.text = currentPost?.title
        self.titleLabel.text = currentPost?.text
        self.dateLabel.text = currentPost?.dateCreated?.timeAgo
        
        self.nameButton.setTitle(currentPost?.user?.name, for: .normal)
        
        let url = URL(string: (currentPost?.user?.profileImage)!)!
        profileImage.contentMode = .scaleAspectFit
        profileImage.af_setImage(withURL: url)
        
    }
    
    /// Add data to the segue before triggering.
    ///
    /// TODO Modify so it works with push and pop
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "ProfileDetailSegue" {
            let nextView = segue.destination as? ProfileDetailController
            nextView?.currentUser = currentPost?.user
        }
    }
}

