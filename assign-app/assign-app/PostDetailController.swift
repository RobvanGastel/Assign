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
/// TODO: Modify so it works with push and pop
class PostDetailController: UIViewController {
    
    @IBOutlet weak var userLabel: UILabel!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var nameButton: UIButton!
    @IBOutlet weak var profileImage: UIImageView!
    @IBOutlet weak var helpButton: UIButton!
    @IBOutlet weak var helpButtonBar: UIView!
    
    // The API service
    var apiService: ApiService?
    
    // Provided data from the segue
    var currentPost:Post?
    
    var delegate: RefreshPostsDelegate?
    
    var replied: Bool? = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Init API service
        apiService = ApiService()
        
        self.initializePost()
    }
    
    func initializePost() {
        // Set the data to the labels in the view
        self.userLabel.text = currentPost?.title
        self.titleLabel.text = currentPost?.text
        self.dateLabel.text = currentPost?.dateCreated?.timeAgo
        self.nameButton.setTitle(currentPost?.user?.name, for: .normal)
        
        let url = URL(string: (currentPost?.user?.profileImage)!)!
        let filter = AspectScaledToFillSizeFilter(size: profileImage.frame.size)
        profileImage.af_setImage(withURL: url, filter: filter)
        
        // Handle own post settings
        if (currentPost?.user?.id == Storage.getUser().id ||
            (currentPost?.replies?.contains(Storage.getUser().id))!) {
            self.helpButton.isHidden = true
            self.helpButtonBar.isHidden = true
        }
    }
    
    /// Set StatusBartStyle to .default and sets navigationbar.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        // Layout settings
        UIApplication.shared.statusBarStyle = .default
        self.navigationController?.setNavigationBarHidden(false, animated: true)
        self.navigationController?.navigationBar.barTintColor = UIColor(red: 245/255, green: 245/255, blue: 245/255, alpha: 1)
    }
    
    /// Add data to the segue before triggering.
    ///
    /// TODO: Modify so it works with push and pop
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "ProfileDetailSegue" {
            let nextView = segue.destination as? ProfileDetailController
            nextView?.currentUser = currentPost?.user
        }
    }
    
    /// To share a post with Facebook, Twitter etc.
    @IBAction func shareAction(_ sender: Any) {
        
        let postText = (currentPost?.user?.name)! + " vraagt om hulp bij " + (currentPost?.title)!
        let postUrl : NSURL = NSURL(string: currentPost!.url)!
        
        // Fills in the Image, text and url
        let activityViewController : UIActivityViewController = UIActivityViewController(
            activityItems: [postText, postUrl], applicationActivities: nil)
        
        // This lines is for the popover
        // activityViewController.popoverPresentationController?.barButtonItem = (sender as! UIBarButtonItem)
        
        // This line remove the arrow of the popover
        activityViewController.popoverPresentationController?.sourceRect = CGRect(x: 150, y: 150, width: 0, height: 0)
        
        // All activities to exclude of the share
        activityViewController.excludedActivityTypes = [
            UIActivityType.assignToContact,
            UIActivityType.postToFlickr,
            UIActivityType.postToVimeo,
            UIActivityType.openInIBooks,
            UIActivityType.postToTencentWeibo,
            UIActivityType.postToWeibo,
            UIActivityType.print,
            UIActivityType.saveToCameraRoll
        ]
        
        // Present the share options
        self.present(activityViewController, animated: true, completion: nil)
    }
    
    @IBAction func helpAction(_ sender: Any) {
        apiService?.addReply(id: (self.currentPost?.id)!) { success in
            
            if success {
                self.helpButton.isHidden = true
                self.helpButtonBar.isHidden = true
                
                self.delegate?.refreshPosts()
                // TODO: Handle button clicked
                
            } else {
                // TODO: Error response
            }
        }
    }
    
    @IBAction func backAction(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
}

