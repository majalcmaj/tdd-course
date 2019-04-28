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
}

struct MeasurementList: Decodable {
    
    let status: String
    let message: String
    let data: [Measurement]?
    
}
