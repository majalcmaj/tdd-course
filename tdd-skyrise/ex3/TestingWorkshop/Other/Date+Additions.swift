//
//  Date+Additions.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 09/01/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import Foundation

extension Date {
    
    public var stringValue: String {
        let fmt = DateFormatter()
        fmt.dateFormat = "yyyy-MM-dd"
        return fmt.string(from: self)
    }
    
}
