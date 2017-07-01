//
//  UIController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 27/06/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

/// UIView extension to hide keyboard on tap.
extension UIViewController {
    
    func hideKeyboardWhenTappedAround() {
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(UIViewController.dismissKeyboard))
        tap.cancelsTouchesInView = false
        view.addGestureRecognizer(tap)
    }
    
    func dismissKeyboard() {
        view.endEditing(true)
    }
}
