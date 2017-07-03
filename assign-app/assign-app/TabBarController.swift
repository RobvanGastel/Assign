//
//  TabBarController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 29/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class TabBarController: UITabBarController {

    override func viewDidLoad() {
        super.viewDidLoad()

        let color = UIColor.darkGray // Color for the tab bar
        
        for item in self.tabBar.items! { // Set the color of the text and the icon
            item.image = item.selectedImage?.imageWithColor(color1: color).withRenderingMode(UIImageRenderingMode.alwaysOriginal)
    
            let attributes = [NSForegroundColorAttributeName: color]
            //let attributesDark = [NSForegroundColorAttributeName: colorDark]
            item.setTitleTextAttributes(attributes, for: UIControlState.normal)
    
            //To change the selected TabBar ItemText
            //item.setTitleTextAttributes(attributesDark, forState: UIControlState.Selected)
        }
    }
}

