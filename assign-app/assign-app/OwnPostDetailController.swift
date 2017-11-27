//
//  OwnPostDetailController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 06/09/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit
import PopupKit
import AlamofireImage

/// Controller to view the details of a post.
/// TODO: Add edit functionality
/// TODO: Add refresh to posts
class OwnPostDetailController: UIViewController, UITableViewDataSource, UITableViewDelegate, RefreshViewDelegate {
    
    @IBOutlet weak var userLabel: UILabel!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var nameButton: UIButton!
    @IBOutlet weak var profileImage: UIImageView!
    @IBOutlet weak var ScrollView: UIScrollView!
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var replyCountLabel: UILabel!
    @IBOutlet weak var tableHeight: NSLayoutConstraint! // tableHeight of replies
    
    @IBOutlet weak var endAssignmentButtonBar: UIView!
    @IBOutlet weak var endAssignmentButton: UIButton!
    
    // The API service
    var apiService: ApiService?
    
    // Provided data from the segue
    var currentPost:Post?
    var replies: [Reply] = []
    var popupView: PopupView?
    
    var delegate: RefreshPostsDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Init API service
        apiService = ApiService()
        
        // Initializes the delegates
        tableView.delegate = self
        tableView.dataSource = self

        apiService?.getRepliesByPost(id: currentPost!.id) { replies in
            self.replies = replies!
            self.tableView.reloadData()
            self.updateReplies()
        }
        
        // Enabled scroll
        ScrollView.isScrollEnabled = true
        ScrollView.alwaysBounceVertical = true
        
        self.initializePost()
    }
    
    /// Set StatusBartStyle to .default and sets navigationbar.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        UIApplication.shared.statusBarStyle = .default
        
        // Layout settings
        self.navigationController?.setNavigationBarHidden(false, animated: true)
        self.navigationController?.navigationBar.barTintColor = UIColor(red: 245/255, green: 245/255, blue: 245/255, alpha: 1)
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
        
        if (currentPost?.done)! {
            // TODO: Improve UI disabled button
            self.endAssignmentButtonBar.isHidden = true
            self.endAssignmentButton.isHidden = true
        }
    }
    
    /// Add data to the segue before triggering.
    ///
    /// TODO: Modify so it works with push and pop
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "ProfileDetailSegue" {
            let nextView = segue.destination as? ProfileDetailController
            nextView?.currentUser = currentPost?.user
        }
        
        if segue.identifier == "ProfileSegue",
            let nextView = segue.destination as? ProfileDetailController,
            let indexPath = self.tableView.indexPathForSelectedRow {
            let reply = self.replies[indexPath.row]
            nextView.currentUser = reply.user
        }
    }
    
    /// To share a post with Facebook, Twitter etc.
    @IBAction func settingsAction(_ sender: Any) {
        
        // Create the AlertController
        let actionSheetController = UIAlertController(title: nil,message: nil,  preferredStyle: .actionSheet)
        
        // Create and add the Cancel action
        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel) { action -> Void in
            // Just dismiss the action sheet
        }
        actionSheetController.addAction(cancelAction)
        
        // Edit action
        let editAction = UIAlertAction(title: "Pas aan", style: .default) { action -> Void in
            // Edit action
        }
        actionSheetController.addAction(editAction)
        
        // Share your assignment action
        let shareAction = UIAlertAction(title: "Deel jouw assignment", style: .default) { action -> Void in
            
            let postText = (self.currentPost?.user?.name)! + " vraagt om hulp bij " + (self.currentPost?.title)!
            let postUrl : NSURL = NSURL(string: self.currentPost!.url)!
            
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
        actionSheetController.addAction(shareAction)
        
        // Delete assignment action
        let deleteAction = UIAlertAction(title: "Verwijder de assignment", style: .destructive) { action -> Void in
            
            let alert = UIAlertController(title: "Verwijder de assignment", message: "Weet je zeker dat je de assignment wilt verwijderen?", preferredStyle: .alert)
            
            let clearAction = UIAlertAction(title: "Verwijder", style: .destructive) { (alert: UIAlertAction!) -> Void in
                
                self.apiService?.deletePost(id: self.currentPost!.id) {_ in
                
                    self.navigationController?.popViewController(animated: true)
                    self.delegate?.refreshPosts()
                }
            }
            
            let cancelAction = UIAlertAction(title: "Cancel", style: .default) { (alert: UIAlertAction!) -> Void in }
            
            alert.addAction(clearAction)
            alert.addAction(cancelAction)
            
            self.present(alert, animated: true, completion: nil)
        }
        
        actionSheetController.addAction(deleteAction)
    
        // We need to provide a popover sourceView when using it on iPad
        actionSheetController.popoverPresentationController?.sourceView = sender as? UIView
        
        // Present the AlertController
        self.present(actionSheetController, animated: true, completion: nil)
    }
    
    @IBAction func backAction(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }

    // MARK: - Table view with replies
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return replies.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ReplyCell", for: indexPath as IndexPath)
        
        let reply = replies[indexPath.row]
        
        if let studyLabel = cell.viewWithTag(401) as? UILabel {
            studyLabel.text = reply.user.specialisation
        }
        
        if let titleLabel = cell.viewWithTag(402) as? UILabel {
            titleLabel.text = reply.user.name
        }
        
        if let profileImage = cell.viewWithTag(404) as? UIImageView {
            let url = URL(string: (reply.user?.profileImage)!)!
            let filter = AspectScaledToFillSizeFilter(size: profileImage.frame.size)
            profileImage.af_setImage(withURL: url, filter: filter)
        }
        
        return cell
    }
    
    override func updateViewConstraints() {
        super.updateViewConstraints()
        tableHeight?.constant = (CGFloat(64 * replies.count))
    }
    
    func updateReplies() {
        self.updateViewConstraints()
        
        if replies.count == 0 {
            replyCountLabel.text = "Nog geen hulp aangeboden"
            endAssignmentButtonBar.isHidden = true
            endAssignmentButton.isHidden = true
        } else if replies.count == 1 {
             replyCountLabel.text = "Iemand wilt jou helpen"
        } else if replies.count >= 2 {
            replyCountLabel.text =  String(replies.count) + " Mensen willen jou helpen"
        }
    }
    
    @IBAction func endAssignment(_ sender: Any) {
        
        // Create height with constraint to make it as big as screen
        let view = PopupReplyController.init(frame: CGRect(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 248 + (64*2)))
        
        view.setData(replies: self.replies, post: self.currentPost!, delegate: self)
        
        let layout = PopupView.Layout.init(horizontal: PopupView.HorizontalLayout.center, vertical: PopupView.VerticalLayout.bottom)
        
        popupView = PopupView(contentView: view, showType: PopupView.ShowType.slideInFromBottom, dismissType: PopupView.DismissType.slideOutToBottom, maskType: PopupView.MaskType.dimmed, shouldDismissOnBackgroundTouch: true, shouldDismissOnContentTouch: false)
        // Do any additional setup after loading the view, typically from a nib.
        
        popupView?.show(with: layout)
    }
    
    func refreshView() {
        // TODO: Update the post that is now completed
        popupView?.dismiss(animated: true)

        self.endAssignmentButtonBar.isHidden = true
        self.endAssignmentButton.isHidden = true
        
        delegate?.refreshPosts()
    }
}
