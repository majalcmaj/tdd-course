//
//  Date+Additions.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 09/01/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import Foundation

extension Date {
    
    var stringValue: String {
        let fmt = DateFormatter()
        fmt.dateFormat = "yyyy-MM-dd"
        return fmt.string(from: self)
    }
    
    func stringValue(withFormat format: String) -> String? {
        if format.isEmpty {
            return nil
        }
        let fmt = DateFormatter()
        fmt.dateFormat = format
        return fmt.string(from: self)
    }
    
}
