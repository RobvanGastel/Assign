//
//  View.swift
//  assign-app
//
//  Created by Max Wammes on 14/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit


// Standard classes
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
        // self.image = #imageLiteral(resourceName: "profile.jpg")
    }
}

class UIName: UILabel {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.textColor = UIColor(red: 0.3, green: 0.3, blue: 0.3, alpha: 1)
    }
}

class UITitle: UILabel {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.textColor = UIColor(red: 0.36, green: 0.36, blue: 0.36, alpha: 1)
        
    }
}

class UIDate: UILabel {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.textColor = UIColor(red: 0.69, green: 0.69, blue: 0.69, alpha: 1)
    }
}


// Detail page classes
class UIOverviewDetail: UIView {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        layer.borderWidth = 1
        layer.cornerRadius = 12
        layer.borderColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
    }
}

class UIProfileDetail: UIImageView {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.layer.cornerRadius = 16
        self.layer.masksToBounds = true
        self.image = #imageLiteral(resourceName: "profile.jpg")
    }
}

class UIDescriptionDetail: UILabel {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.textColor = UIColor(red: 0.3, green: 0.3, blue: 0.3, alpha: 1)
    }
}


// Profile page classes
class UIProfilePage: UIImageView {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.layer.cornerRadius = 32
        self.layer.masksToBounds = true
        self.image = #imageLiteral(resourceName: "profile.jpg")
    }
}
