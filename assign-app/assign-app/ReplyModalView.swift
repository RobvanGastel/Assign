//
//  ReplyModalView.swift
//  assign-app
//
//  Created by Max Wammes on 07/11/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import UIKit

class ReplyModalView: UIView {

    @IBOutlet var contentView: UIView!
    
    override init (frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        commonInit()
    }
    
    private func commonInit() {
        Bundle.main.loadNibNamed("ReplyModalView", owner: self, options: nil)
        addSubview(contentView)
        contentView.frame = self.bounds
        contentView.autoresizingMask = [.flexibleHeight, .flexibleWidth]
    }

}
