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

class PopupReplyController: UIView, UITableViewDataSource, UITableViewDelegate {
    
    @IBOutlet var contentView: UIView!
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var titleLabel: UILabel!
    
    // Replies in popup
    var replies: [Reply] = []
    var post: Post?
 
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
        
        // Set delegates
        self.tableView.delegate = self
        self.tableView.dataSource = self
        
        // Init xib file
        self.tableView.register(UINib(nibName: "ReplyCell", bundle: nil), forCellReuseIdentifier: "ReplyModalCell")
    }
    
    /// Set data for the Popup
    func setData(replies: [Reply], post: Post) {
        self.replies = replies
        self.post = post
        self.tableView.reloadData()
        
        titleLabel.text = post.title
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        commonInit()
    }
    
    private func commonInit() {
        Bundle.main.loadNibNamed("ReplyModalView", owner: self, options: nil)
        addSubview(contentView)
        contentView.frame = self.bounds
        contentView.layer.cornerRadius = 12.0
        contentView.autoresizingMask = [.flexibleHeight, .flexibleWidth]
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
    
         if cell.checkboxImage.image == #imageLiteral(resourceName: "icon-reply-unchecked.png") {
             cell.checkboxImage.image = #imageLiteral(resourceName: "icon-reply-checked.png")
             reply.helped = true
         } else {
             cell.checkboxImage.image = #imageLiteral(resourceName: "icon-reply-unchecked.png")
             reply.helped = false
         }
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
}
