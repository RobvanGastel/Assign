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

        let color = UIColor.lightGray // Color for the tab bar
        
        UITabBarItem.appearance().setTitleTextAttributes([NSForegroundColorAttributeName: UIColor.lightGray], for:.normal)
        
        UITabBarItem.appearance().setTitleTextAttributes([NSForegroundColorAttributeName: UIColor.orange], for:.selected)
        
        for item in self.tabBar.items! { // Set the color of the text and the icon
            item.image = item.selectedImage?.imageWithColor(color1: color).withRenderingMode(UIImageRenderingMode.alwaysOriginal)
    
            let attributes = [NSForegroundColorAttributeName: color]
            item.setTitleTextAttributes(attributes, for: UIControlState.normal)
        }
    }
}
