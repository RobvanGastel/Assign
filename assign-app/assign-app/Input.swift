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
        
        layer.cornerRadius = 6
        layer.sublayerTransform = CATransform3DMakeTranslation(16, 0, 0);
    }
}
