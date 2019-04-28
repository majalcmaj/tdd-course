//
//  MeasurementStationsServiceProtocol.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 08/04/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import Foundation

protocol MeasurementStationsServiceProtocol {
    
    func getStations(success: @escaping (StationList) -> Void, failure: @escaping (Error?) -> Void)
    func getMeasurement(station: Station, symbol: MeasurementSymbol, success: @escaping (MeasurementList) -> Void, failure: @escaping (Error?) -> Void)
    
}
