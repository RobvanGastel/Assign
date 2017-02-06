//
//  OverviewTableViewController.swift
//  assign-app
//
//  Created by Rob Van Gastel on 16/01/17.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class OverviewTableViewController: UITableViewController {

    
    var posts = [Post]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //loadJsonData()
        
        posts = [
            Post(id: 1, title: "What Makes Flyers Unrivaled", user: "Landon Gordon", date: "Just now"),
            Post(id: 2, title: "5 Reasons To Purchase Desktop ComputersDirectory Add Url Free", user: "Stanley Henderson", date: "24m")]
        
        
        navigationController?.navigationBar.barTintColor = UIColor(red: 255, green: 255, blue: 255, alpha: 1)

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return posts.count
    }
    
    //Example API call
    func loadJsonData()
    {
        let urlString: String = "https://localhost:8084/api/posts/"
        
        //Guard checks for a valid URL
        guard let url = URL(string: urlString) else {
            print("Error: cannot create URL")
            return
        }
        
        let urlRequest = URLRequest(url: url)
        let session = URLSession.shared
        let task = session.dataTask(with: urlRequest) { data, response, error in
            // check for any errors
            guard error == nil else {
                print("error calling GET on /todos/1")
                print(error!)
                return
            }
            // check if data exists
            guard let responseData = data else {
                print("Error: did not receive data")
                return
            }
            
            // parse the result as JSON
            do {
                guard let posts = try JSONSerialization.jsonObject(with: responseData, options: [])
                    as? [String: Any] else {
                        print("error trying to convert data to JSON")
                        return
                }
                print(posts)
                
            } catch  {
                print("error trying to convert data to JSON")
                return
            }
        }
        task.resume()
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        
        let post = posts[indexPath.row] as Post
        
        if let titleLabel = cell.viewWithTag(101) as? UILabel {
            titleLabel.text = post.title
        }
        
        if let nameLabel = cell.viewWithTag(102) as? UILabel {
            nameLabel.text = post.user
        }
        
        if let dateLabel = cell.viewWithTag(103) as? UILabel {
            dateLabel.text = post.date
        }
        
        return cell
    }
 

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
