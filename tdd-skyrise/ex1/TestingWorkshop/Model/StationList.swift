//
//  StationList.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 09/01/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import Foundation

struct StationList: Codable {
    
    let status: String
    let message: String
    var data: [Station]
    
}
