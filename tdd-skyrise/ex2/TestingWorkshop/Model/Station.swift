//
//  Station.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 09/01/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import Foundation

struct Station: Codable {
    
    let no: Int
    let name: String
    let active: Bool
    let rain: Bool
    let water: Bool
    let winddir: Bool
    let windlevel: Bool
    
    var activeSymbols: [MeasurementSymbol] {
        var symbols = [MeasurementSymbol]()
        
        if rain {
            symbols.append(.rain)
        }
        
        if water {
            symbols.append(.water)
        }
        
        if winddir {
            symbols.append(.winddir)
        }
        
        if windlevel {
            symbols.append(.windlevel)
        }
        
        return symbols
    }
    
}
