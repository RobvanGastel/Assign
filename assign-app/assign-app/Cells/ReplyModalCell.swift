//
//  ReplyModalCellTableViewCell.swift
//  assign-app
//
//  Created by Rob Van Gastel on 08/11/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class ReplyModalCell: UITableViewCell {

    @IBOutlet weak var nameLabel: UIName!
    @IBOutlet weak var studyLabel: UITitle!
    @IBOutlet weak var profileImage: UIProfile!
    @IBOutlet weak var checkboxImage: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
