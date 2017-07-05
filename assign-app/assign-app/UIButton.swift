//
//  UIButton.swift
//  assign-app
//
//  Created by Rob Van Gastel on 04/07/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

extension UIButton {
    
    
    /// Increase the hitbox of buttons to 44 px.
    open override func hitTest(_ point: CGPoint, with event: UIEvent?) -> UIView? {

        let minimumHitArea = CGSize(width: 100, height: 100)
        
        // if the button is hidden/disabled/transparent it can't be hit
        if self.isHidden || !self.isUserInteractionEnabled || self.alpha < 0.01 { return nil }
        
        // increase the hit frame to be at least as big as `minimumHitArea`
        let buttonSize = self.bounds.size
        let widthToAdd = max(minimumHitArea.width - buttonSize.width, 0)
        let heightToAdd = max(minimumHitArea.height - buttonSize.height, 0)
        let largerFrame = self.bounds.insetBy(dx: -widthToAdd / 2, dy: -heightToAdd / 2)
        
        // perform hit test on larger frame
        return (largerFrame.contains(point)) ? self : nil
    }
}
