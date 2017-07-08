//
//  PostDetailController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 26/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

 
import UIKit
import AlamofireImage

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
        let filter = AspectScaledToFillSizeFilter(size: profileImage.frame.size)
        profileImage.af_setImage(withURL: url, filter: filter)
    }
    
    /// Set StatusBartStyle to .default and sets navigationbar.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        UIApplication.shared.statusBarStyle = .default
        
        self.navigationController?.setNavigationBarHidden(false, animated: true)
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
    
    /// To share a post with Facebook, Twitter etc.
    ///
    /// TODO Add full functionality :
    /// An image, text, Logo and URL.
    @IBAction func shareAction(_ sender: Any) {
        
        // TODO add custom text to share
        let postText = "USER vraagt om hulp met TITLE, kan jij helpen?"
        // TODO Add custom url to match the post
        let postUrl : NSURL = NSURL(string: "http://84.26.134.115:8080/api/posts/\(currentPost!.id)")!
        // TODO Add the assign logo
        let image : UIImage = UIImage(named: "app-logo.png")!
        
        // Fills in the Image, text and url
        let activityViewController : UIActivityViewController = UIActivityViewController(
            activityItems: [postText, postUrl, image], applicationActivities: nil)
        
        // This lines is for the popover
        activityViewController.popoverPresentationController?.barButtonItem = (sender as! UIBarButtonItem)
        
        // This line remove the arrow of the popover
        activityViewController.popoverPresentationController?.sourceRect = CGRect(x: 150, y: 150, width: 0, height: 0)
        
        // All activities to exclude of the share
        activityViewController.excludedActivityTypes = [
            UIActivityType.assignToContact,
            UIActivityType.postToFlickr,
            UIActivityType.postToVimeo,
            UIActivityType.openInIBooks,
            UIActivityType.copyToPasteboard,
            UIActivityType.postToTencentWeibo,
            UIActivityType.postToWeibo,
            UIActivityType.print,
            UIActivityType.airDrop,
            UIActivityType.saveToCameraRoll
        ]
        
        // Present the share options
        self.present(activityViewController, animated: true, completion: nil)
    }
}

