//
//  Add.swift
//  assign-app
//
//  Created by Max Wammes on 16/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class UIAddDesc: UITextView {
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.text = "Geef een beschrijving"
        self.textColor = UIColor(red: 0.67, green: 0.67, blue: 0.67, alpha: 1)
        layer.sublayerTransform = CATransform3DMakeTranslation(0, 0, 0);
    }
}
