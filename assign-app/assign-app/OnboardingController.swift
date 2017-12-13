//
//  OnboardingController.swift
//  assign-app
//
//  Created by Max Wammes on 31/10/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class OnboardingController: UIPageViewController, UIPageViewControllerDelegate, UIPageViewControllerDataSource {

    // Pager viewcontrollers
    lazy var orderedViewControllers: [UIViewController] = {
        return [self.newViewController(viewController: "StepOne"),
                self.newViewController(viewController: "StepTwo"),
                self.newViewController(viewController: "StepThree"),
                self.newViewController(viewController: "StepFour")]
    }()
    
    // Page controller
    var pageControl = UIPageControl()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Assign delegates
        self.dataSource = self
        self.delegate = self
        
        // Configure the buttons and pager styling
        self.configurePageControl()
        self.initializeButtons()
        
        // Set the pages
        if let firstViewController = orderedViewControllers.first {
            setViewControllers([firstViewController],
                               direction: .forward,
                               animated: true,
                               completion: nil)
        }
    }
    
    // Action of the skip button
    func skipButtonAction(sender: UIButton!) {
//        let transition = CATransition()
//        transition.duration = 0.1
//        transition.type = kCATransitionMoveIn
//        transition.subtype = kCATransitionFromRight
//        self.view.layer.add(transition, forKey: kCATransition)
        self.redirectViewController(animated: true, identifier: "LoginController")
    }
    
    // Action of the next button
    func nextButtonAction(sender: UIButton!) {
        if let currentViewController = viewControllers?[0] {
            
            if self.orderedViewControllers.count - 1 == self.pageControl.currentPage {
                Storage.setFirstLaunchTime()
                self.redirectViewController(animated: true, identifier: "LoginController")
            }
            
            if let nextPage = dataSource?.pageViewController(self, viewControllerAfter: currentViewController) {
                setViewControllers([nextPage], direction: .forward, animated: true) { result in
                    
                    // First time check if the app is allowed to send notifications
                    if self.orderedViewControllers.index(of: nextPage) == 3 {
                        self.registerAndChangeButton()
                    }
                    
                    self.pageControl.currentPage = self.orderedViewControllers.index(of: nextPage)!
                }
            }
        }
    }
    
    /// Redirect to another view.
    func redirectViewController(animated: Bool, identifier: String) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: identifier)
        self.present(vc, animated: animated, completion: nil)
    }

    /// Instatiate ViewController
    func newViewController(viewController: String) -> UIViewController {
        return UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier:viewController)
    }
    
    // MARK: - Pager implementation
    
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerBefore viewController: UIViewController) -> UIViewController? {
        
        guard let viewControllerIndex = orderedViewControllers.index(of: viewController) else {
            return nil
        }
        
        let previousIndex = viewControllerIndex - 1
        
        // This will run before the first page
        guard previousIndex >= 0 else {
            return nil
        }
        
        guard orderedViewControllers.count > previousIndex else {
            return nil
        }
        
        return orderedViewControllers[previousIndex]
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerAfter viewController: UIViewController) -> UIViewController? {
        
        guard let viewControllerIndex = orderedViewControllers.index(of: viewController) else {
            self.redirectViewController(animated: true, identifier: "LoginController")
            return nil
        }
        
        let nextIndex = viewControllerIndex + 1

        // This will run after the last page
        guard orderedViewControllers.count != nextIndex else {
            return nil
        }

        guard orderedViewControllers.count > nextIndex else {
            return nil
        }
        
        return orderedViewControllers[nextIndex]
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, didFinishAnimating finished: Bool, previousViewControllers: [UIViewController], transitionCompleted completed: Bool) {
        let pageContentViewController = pageViewController.viewControllers![0]
        self.pageControl.currentPage = orderedViewControllers.index(of: pageContentViewController)!
        
        // First time check if the app is allowed to send notifications
        if orderedViewControllers.index(of: pageContentViewController) == 3 {
            registerAndChangeButton()
        }
    }
    
    func registerAndChangeButton() {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.registerForPushNotifications()
        
        if let nextButton = view.viewWithTag(251) as? UIButton {
            nextButton.setTitle("Begrepen", for: [])
        }
    }
    
    // MARK: - Styling of controller
    
    // Styling of pageControls
    func configurePageControl() {
        pageControl = UIPageControl(frame: CGRect(x: 0, y: UIScreen.main.bounds.maxY - 108, width: UIScreen.main.bounds.width, height: 8))
        pageControl.numberOfPages = orderedViewControllers.count
        pageControl.currentPage = 0
        pageControl.tintColor = UIColor(red: 0.48, green: 0.48, blue: 0.48, alpha: 1)
        pageControl.pageIndicatorTintColor = UIColor(red: 0.88, green: 0.88, blue: 0.88, alpha: 1)
        pageControl.currentPageIndicatorTintColor = UIColor(red: 0.48, green: 0.48, blue: 0.48, alpha: 1)
        self.view.addSubview(pageControl)
    }
    
    // Initialize the buttons
    func initializeButtons() {
        
        // Display the skip button "Overslaan"
        let skipButton = UIButton(frame: CGRect(x: 24, y: UIScreen.main.bounds.maxY - 76, width: UIScreen.main.bounds.width/2 - 32, height: 52))
        skipButton.backgroundColor = UIColor(red: 0.96, green: 0.96, blue: 0.96, alpha: 1)
        skipButton.setTitleColor(UIColor(red: 0.48, green: 0.48, blue: 0.48, alpha: 1), for: .normal)
        skipButton.setTitleColor(UIColor(red: 0.48, green: 0.48, blue: 0.48, alpha: 0.7), for: .highlighted)
        skipButton.setTitle("Overslaan", for: [])
        skipButton.layer.cornerRadius = 6
        skipButton.titleLabel?.font = UIFont.boldSystemFont(ofSize: 16)
        skipButton.addTarget(self, action: #selector(skipButtonAction), for: .touchUpInside)
        skipButton.tag = 250
        self.view.addSubview(skipButton)
        
        // Display the next button "Volgende"
        let nextButton = UIButton(frame: CGRect(x: UIScreen.main.bounds.width/2 + 8, y: UIScreen.main.bounds.maxY - 76, width: UIScreen.main.bounds.width/2 - 32, height: 52))
        nextButton.backgroundColor = UIColor(red: 1, green: 0.5, blue: 0.156, alpha: 1)
        nextButton.setTitleColor(UIColor(red: 1, green: 1, blue: 1, alpha: 1), for: .normal)
        nextButton.setTitleColor(UIColor(red: 1, green: 1, blue: 1, alpha: 0.7), for: .highlighted)
        nextButton.setTitle("Volgende", for: [])
        nextButton.layer.cornerRadius = 6
        nextButton.titleLabel?.font = UIFont.boldSystemFont(ofSize: 16)
        nextButton.addTarget(self, action: #selector(nextButtonAction), for: .touchUpInside)
        nextButton.tag = 251
        self.view.addSubview(nextButton)
        
        self.view.backgroundColor = UIColor.white
    }
}
