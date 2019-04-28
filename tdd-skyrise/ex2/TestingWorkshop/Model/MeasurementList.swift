//
//  MeasurementList.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 09/01/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import Foundation

enum MeasurementSymbol: String {
    case rain = "rain"
    case water = "water"
    case winddir = "winddir"
    case windlevel = "windlevel"
    
    init(message: String) {
        let symbol: MeasurementSymbol
        
        switch message {
        case "Wartości opadu":
            symbol = .rain
        case "Wartości prędkości wiatru":
            symbol = .windlevel
        case "Wartości kierunku wiatru":
            symbol = .winddir
        case "Wartości poz. wody":
            symbol = .water
        default:
            symbol = .water
        }
        
        self = symbol
    }
}

struct MeasurementList: Decodable {
    
    let status: String
    let message: String
    let data: [Measurement]?
    
    var symbol: MeasurementSymbol {
        return MeasurementSymbol(message: message)
    }
}
