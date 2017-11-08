//
//  PopupReplyController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 07/11/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation
import AlamofireImage

class PopupReplyController: UIView, UITableViewDataSource, UITableViewDelegate {
    
    @IBOutlet var contentView: UIView!
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var titleLabel: UILabel!
    
    var replies: [Reply] = []
    var post: Post?
 
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
        
        self.tableView.delegate = self
        self.tableView.dataSource = self
        // tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cell")
        self.tableView.register(UINib(nibName: "ReplyCell", bundle: nil), forCellReuseIdentifier: "ReplyModalCell")
    }
    
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
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return replies.count
    }
    
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
