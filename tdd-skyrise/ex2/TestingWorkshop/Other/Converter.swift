//
//  Converter.swift
//  TestingWorkshop
//
//  Created by Krzysztof on 11/04/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import Foundation

class Converter {
    
    func convertDegreesToWorldDirection(degrees: Float) -> String {
        if (degrees >= 0 && degrees <= 45) || (degrees > 315 && degrees <= 360) {
            return  "North"
        } else if degrees > 45 && degrees <= 135 {
            return "East"
        } else if degrees > 135 && degrees <= 225 {
            return "South"
        }
        return "West"
    }
}
