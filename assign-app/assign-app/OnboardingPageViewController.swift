//
//  OnboardingPageViewController.swift
//  assign-app
//
//  Created by Max Wammes on 31/10/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class OnboardingPageViewController: UIPageViewController, UIPageViewControllerDelegate, UIPageViewControllerDataSource {

    lazy var orderedViewControllers: [UIViewController] = {
        return [self.newVc(viewController: "StepOne"),
                self.newVc(viewController: "StepTwo"),
                self.newVc(viewController: "StepThree"),
                self.newVc(viewController: "StepFour")]
    }()
    
    var pageControl = UIPageControl()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.dataSource = self
        if let firstViewController = orderedViewControllers.first {
            setViewControllers([firstViewController],
                               direction: .forward,
                               animated: true,
                               completion: nil)
        }
        
        // Display the skip button "Overslaan"
        let skipButton = UIButton(frame: CGRect(x: 24, y: UIScreen.main.bounds.maxY - 76, width: UIScreen.main.bounds.width/2 - 32, height: 52))
        skipButton.backgroundColor = UIColor(red: 0.48, green: 0.48, blue: 0.48, alpha: 1)
        skipButton.setTitle("Overslaan", for: [])
        skipButton.layer.cornerRadius = 6
        self.view.addSubview(skipButton)
        
        // Display the next button "Volgende"
        let nextButton = UIButton(frame: CGRect(x: UIScreen.main.bounds.width/2 + 8, y: UIScreen.main.bounds.maxY - 76, width: UIScreen.main.bounds.width/2 - 32, height: 52))
        nextButton.backgroundColor = UIColor(red: 1, green: 0.5, blue: 0.156, alpha: 1)
        nextButton.setTitle("Volgende", for: [])
        nextButton.layer.cornerRadius = 6
        self.view.addSubview(nextButton)
        
        self.delegate = self
        configurePageControl()
        
        self.view.backgroundColor = UIColor.white
    }
    
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

    func newVc(viewController: String) -> UIViewController {
        return UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier:viewController)
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerBefore viewController: UIViewController) -> UIViewController? {
        guard let viewControllerIndex = orderedViewControllers.index(of: viewController) else {
            return nil
        }
        
        let previousIndex = viewControllerIndex - 1
        
        // This will run before the first page
        guard previousIndex >= 0 else {
            //return orderedViewControllers.last
            return nil
        }
        
        guard orderedViewControllers.count > previousIndex else {
            return nil
        }
        
        return orderedViewControllers[previousIndex]
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerAfter viewController: UIViewController) -> UIViewController? {
        guard let viewControllerIndex = orderedViewControllers.index(of: viewController) else {
            return nil
        }
        
        let nextIndex = viewControllerIndex + 1
        
        // This will run after the last page
        guard orderedViewControllers.count != nextIndex else {
            //return orderedViewControllers.first
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
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
