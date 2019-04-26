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
    
    func symbol(for message: String?) -> MeasurementSymbol? {
        guard let message = message else { return nil }
        
        switch message {
        case "Wartości opadu":
            return .rain
        case "Wartości prędkości wiatru":
            return .windlevel
        case "Wartości kierunku wiatru":
            return .winddir
        case "Wartości poz. wody":
            return .water
        default:
            return nil
        }
    }
    
}
