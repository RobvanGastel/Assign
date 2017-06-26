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
        
        self.textColor = UIColor(red: 0.66, green: 0.66, blue: 0.66, alpha: 1)
        self.text = "Geef een beschrijving"
    }
    
    func textViewDidBeginEditing(_ UIAddDesc: UITextView) {
        if self.textColor == UIColor(red: 0.66, green: 0.66, blue: 0.66, alpha: 1) {
            self.text = nil
            self.textColor = UIColor(red: 0.44, green: 0.44, blue: 0.44, alpha: 1)
        }
    }
    
    func textViewDidEndEditing(_ UIAddDesc: UITextView) {
        if self.text.isEmpty {
            self.text = "Geef een beschrijving"
            self.textColor = UIColor(red: 0.66, green: 0.66, blue: 0.66, alpha: 1)
        }
    }
}
