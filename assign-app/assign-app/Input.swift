//
//  Input.swift
//  assign-app
//
//  Created by Max Wammes on 27/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class UIInput: UITextField {
    override func awakeFromNib() {
        super.awakeFromNib()
        
//        layer.cornerRadius = 6
//        layer.sublayerTransform = CATransform3DMakeTranslation(16, 0, 0);
        self.borderStyle = .none
        self.layer.backgroundColor = UIColor.white.cgColor
        
        self.layer.masksToBounds = false
        self.layer.shadowColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
        self.layer.shadowOffset = CGSize(width: 0.0, height: 1.0)
        self.layer.shadowOpacity = 1.0
        self.layer.shadowRadius = 0.0
    }
}

class UISearch: UISearchBar {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        layer.borderWidth = 1
        layer.borderColor = UIColor(red: 1, green: 1, blue: 1, alpha: 1).cgColor
        layer.backgroundColor = UIColor(red: 1, green: 1, blue: 1, alpha: 1).cgColor
        layer.shadowColor = UIColor(red: 0.91, green: 0.91, blue: 0.91, alpha: 1).cgColor
        layer.shadowOffset = CGSize(width: 0.0, height: 1.0)
        layer.shadowOpacity = 1.0
        layer.shadowRadius = 0.0
    }
}
