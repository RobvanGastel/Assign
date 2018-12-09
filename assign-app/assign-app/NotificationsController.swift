//
//  NotificationsController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 01/07/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit
import AlamofireImage

/// TODO: Update pull to refresh to minimize (Abstract class)
/// TODO: add Data to Core Data as cache
class NotificationsController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var tableView: UITableView!
    
    // Posts array for tableview
    var notifications = [Notification]()
    
    // Pagination variables
    let size = 21 // Amount of Posts to load next
    var start = 0 // Starting index of the posts
    var isLoading = false // Is currently loading posts
    var reachedEnd = false // Check if there a no new posts
    
    lazy var refreshControl: UIRefreshControl = {
        let refreshControl = UIRefreshControl()
        refreshControl.addTarget(self, action:
            #selector(NotificationsController.handleRefresh(_:)),
                                 for: UIControlEvents.valueChanged)
        refreshControl.tintColor = UIColor(hexString: "#FFA92F")
        
        return refreshControl
    }()
    
    // API service
    var apiService: ApiService?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Init API service
        apiService = ApiService()
        
        tableView.delegate = self
        tableView.dataSource = self
        
        self.tableView.backgroundView = self.refreshControl
        
        // On viewLoad get the posts from the API
        // Starting values of size is 20 and start 1
        self.apiService?.getNotifications(size: size, start: start) { notifications in
            
            // Sets the posts and refreshes the table
            self.notifications = notifications!
            self.tableView.reloadData()
        }
    }
    
    /// Set StatusBartStyle to default.
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        // Layout settings
        UIApplication.shared.statusBarStyle = .default
        view.backgroundColor = UIColor(red: 245/255, green: 245/255, blue: 245/255, alpha: 1)
//        self.navigationController?.popViewController(animated: true)
        self.navigationController?.navigationBar.barTintColor = UIColor(red: 245/255, green: 245/255, blue: 245/255, alpha: 1)
        self.navigationController?.navigationBar.setValue(true, forKey: "hidesShadow")
    }
    
    // Add some space to the TableView
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        self.tableView.contentInset = UIEdgeInsets(top: 5, left: 0, bottom: 5, right: 0)
    }
    
    // MARK: - Table view with Posts
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return notifications.count
    }
    
    /// Segue for Post Detail View
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let notification = notifications[indexPath.row]
        
        // Read notification before entering new controller
        if !notification.readNotification {
            apiService?.setRead(id: notification.id) {}
            
            notifications[indexPath.row].readNotification = true
            self.tableView.reloadData()
        }
        
        // Get post and redirect when retrieved
        apiService?.getPostById(id: notification.postId!) { post in
            
            if notification.sender!.id != Storage.getUser().id {
                let vc = storyboard.instantiateViewController(withIdentifier: "PostDetailController") as! PostDetailController
                vc.currentPost = post
                self.navigationController?.pushViewController(vc, animated: true)
            } else {
                let vc = storyboard.instantiateViewController(withIdentifier: "OwnPostDetailController") as! OwnPostDetailController
                vc.currentPost = post
                self.navigationController?.pushViewController(vc, animated: true)
            }
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "NotificationCell", for: indexPath)
        
        let notification = notifications[indexPath.row] as Notification
        
        if let titleLabel = cell.viewWithTag(101) as? UILabel {
            titleLabel.text = notification.body
        }
        
        if let nameLabel = cell.viewWithTag(102) as? UILabel {
            nameLabel.text = notification.title
        }
        
        if let dateLabel = cell.viewWithTag(103) as? UILabel {
            dateLabel.text = notification.dateCreated?.timeAgoSimple
        }
        
        if let profileImage = cell.viewWithTag(104) as? UIImageView {
            let url = URL(string: (notification.sender?.profileImage)!)!
            let filter = AspectScaledToFillSizeFilter(size: profileImage.frame.size)
            profileImage.af_setImage(withURL: url, filter: filter)
        }
        
        if let isReadView = cell.viewWithTag(105) {
            if !notification.readNotification {
                isReadView.layer.cornerRadius = isReadView.frame.size.width/2
            } else {
                isReadView.isHidden = true
            }
        }
        
        if let isReadViewDot = cell.viewWithTag(106) {
            if !notification.readNotification {
                isReadViewDot.layer.cornerRadius = isReadViewDot.frame.size.width/2
            } else {
                isReadViewDot.isHidden = true
            }
        }
        
        return cell
    }
    
    
    /// Pull and refesh function on the tableView.
    func handleRefresh(_ refreshControl: UIRefreshControl) {
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
            self.refreshNotifications()
        }
    }
    
    /// Refreshes the posts in the view.
    /// Starting values of size is 20 and start 1.
    func refreshNotifications() {
        // Reset infinite loading variables
        self.start = 0
        self.reachedEnd = false
        
        self.apiService?.getNotifications(size: 21, start: start) { notifications in
            
            // TODO add Data to Core Data as cache
            // Reloads the tableView and stops the refresh animation
            self.notifications = notifications!
            self.tableView.reloadData()
            self.refreshControl.endRefreshing()
        }
    }
    
    /// ScrollView infinite scroll.
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        
        // calculate scrollView size
        let currentOffset = scrollView.contentOffset.y
        let maximumOffset = scrollView.contentSize.height - scrollView.frame.size.height
        let deltaOffset = maximumOffset - currentOffset
        
        // If the scrollView is at the bottom load new posts
        if deltaOffset <= 0 {
            self.loadNotifications()
        }
    }
    
    /// Load next posts and add to the tableView
    func loadNotifications() {
        if !isLoading { // Checks if the table is currently loading
            if notifications.count >= 21 && reachedEnd == false { // Need atleast 21 posts
                
                self.isLoading = true
                self.tableView.tableFooterView?.isHidden = false
                
                // Add 20 to load the next posts
                self.start += 20
                apiService?.getNotifications(size: size, start: start) { n in
                    
                    if (n?.count)! < 20 { // Check if all posts found
                        print("API: No new notifications")
                        self.reachedEnd = true
                    }
                    
                    self.notifications += n!
                    
                    self.tableView.reloadData()
                    self.isLoading = false
                    self.tableView.tableFooterView?.isHidden = true
                }
            }
        }
    }
    
    /// Read all notifications API call
    @IBAction func readNotifications(_ sender: Any) {
        var ids :[Int] = []
        
        for notification in notifications {
            ids.append(notification.id)
        }
        
        self.apiService?.setRead(ids: ids) { _ in
            self.loadNotifications()
            self.tableView.reloadData()
        }
    }
}
