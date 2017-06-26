//
//  View.swift
//  assign-app
//
//  Created by Max Wammes on 14/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class UIOverview: UIView {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        layer.borderWidth = 1
        layer.cornerRadius = 8
        layer.borderColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
    }
}

class UIProfile: UIImageView {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.layer.cornerRadius = 10
        self.layer.masksToBounds = true
        self.image = #imageLiteral(resourceName: "profile.jpg")
    }
}

class UIName: UILabel {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.textColor = UIColor(red: 0.37, green: 0.37, blue: 0.37, alpha: 1)
    }
}

class UITitle: UILabel {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.textColor = UIColor(red: 0.38, green: 0.38, blue: 0.38, alpha: 1)
    }
}

class UIDate: UILabel {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.textColor = UIColor(red: 0.69, green: 0.69, blue: 0.69, alpha: 1)
    }
}
