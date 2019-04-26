//
//  DetailViewModel.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 08/04/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import Foundation

class DetailViewModel {
    private let service: MeasurementStationsServiceProtocol
    let station: Station
    var measurements = [MeasurementList]()
    
    init(service: MeasurementStationsServiceProtocol, station: Station) {
        self.service = service
        self.station = station
    }
    
    func getMeasurement(symbol: MeasurementSymbol, completion: @escaping () -> Void) {
        service.getMeasurement(station: station, symbol: symbol, success: { [weak self] (measurement) in
            guard let self = self else { return }
            self.measurements.append(measurement)
            completion()
        }) { (error) in
            print(error?.localizedDescription ?? "Unknown error")
        }
    }
    
}
