//
//  PopupReplyController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 07/11/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit
import PopupKit
import AlamofireImage

/// TODO: Improve scaling
class PopupReplyController: UIView, UITableViewDataSource, UITableViewDelegate {

    @IBOutlet var contentView: UIView!
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var doneView: UIView!
    
    // Done view
    @IBOutlet weak var helpedLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var leftImage: UIImageView!
    @IBOutlet weak var rightImage: UIImageView!
    
    // Refreshing Post delegate
    weak var delegate: RefreshViewDelegate?
    
    // Replies in popup
    var replies: [Reply] = []
    var selectedReply: Reply?
    var post: Post?
    
    // The API Service
    var apiService: ApiService?
 
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
        
        // Set delegates
        self.tableView.delegate = self
        self.tableView.dataSource = self
        
        // Init API service
        apiService = ApiService()
        
        // Init xib file
        self.tableView.register(UINib(nibName: "ReplyCell", bundle: nil), forCellReuseIdentifier: "ReplyModalCell")
        
        // Hide done view
        doneView.isHidden = true
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        commonInit()
    }
    
    private func commonInit() {
        Bundle.main.loadNibNamed("PopupReplyView", owner: self, options: nil)
        addSubview(contentView)
        contentView.frame = self.bounds
        contentView.layer.cornerRadius = 12.0
        contentView.autoresizingMask = [.flexibleHeight, .flexibleWidth]
    }
    
    /// Set data for the Popup
    func setData(replies: [Reply], post: Post, delegate: RefreshViewDelegate) {
        self.replies = replies
        self.post = post
        self.delegate = delegate
        self.tableView.reloadData()
        
        titleLabel.text = post.title
    }
    
    // MARK: - Tableview with Replies
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return replies.count
    }
    
    // Check selected in tableview after selected create popup
    // to check if the user selected the right person.
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let cell = tableView.cellForRow(at: indexPath) as! ReplyModalCell
        let reply = replies[indexPath.row]
    
        cell.checkboxImage.image = #imageLiteral(resourceName: "icon-reply-checked.png")
        reply.helped = true
        selectedReply = reply
        self.showDoneView()
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ReplyModalCell", for: indexPath as IndexPath) as! ReplyModalCell
        
        let reply = replies[indexPath.row]
        
        cell.studyLabel.text = reply.user.specialisation
        cell.nameLabel.text = reply.user.name
        cell.checkboxImage.image = #imageLiteral(resourceName: "icon-reply-unchecked.png")
        
        let url = URL(string: (reply.user?.profileImage)!)!
        let filter = AspectScaledToFillSizeFilter(size: cell.profileImage.frame.size)
        cell.profileImage.af_setImage(withURL: url, filter: filter)
        
        return cell
    }
    
    // MARK: - Done view actions
    
    func showDoneView() {
        // Set text and images
        self.helpedLabel.text = "Heeft " + (selectedReply?.user.name)! + " jou geholpen?"
        self.descriptionLabel.text = "Jouw assignment zal gesloten worden en " + (selectedReply?.user.name)! + " zal bedankt worden voor het helpen."
        
        // Set left image
        let leftUrl = URL(string: (self.selectedReply?.post.user?.profileImage)!)!
        let leftFilter = AspectScaledToFillSizeFilter(size: self.leftImage.frame.size)
        self.leftImage.af_setImage(withURL: leftUrl, filter: leftFilter)
        
        // Set right image
        let rightUrl = URL(string: (selectedReply?.user?.profileImage)!)!
        let rightFilter = AspectScaledToFillSizeFilter(size: self.leftImage.frame.size)
        self.rightImage.af_setImage(withURL: rightUrl, filter: rightFilter)
        
        self.doneView.alpha = 0
        self.doneView.isHidden = false
        UIView.animate(withDuration: 0.4) {
            self.doneView.alpha = 1
        }
    }
    
    func hideDoneView() {
        self.tableView.reloadData()
        
        UIView.animate(withDuration: 0.4, animations: {
            self.doneView.alpha = 0
        }) { (finished) in
            self.doneView.isHidden = finished
        }
    }
    
    @IBAction func backAction(_ sender: Any) {
        hideDoneView()
    }
    
    @IBAction func doneAction(_ sender: Any) {
        
        // Set helped with reply
        self.apiService?.setHelped(id: self.selectedReply!.id) { _ in }
        
        // Set done of the assignment
        self.apiService?.setDone(id: self.post!.id) {_ in }
        
        // Call protocol to refresh the Post and set Done
        delegate?.refreshView()
    }
}
