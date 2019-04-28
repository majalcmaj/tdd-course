//
//  MeasurementStationsServiceMock.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 08/04/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import Foundation

class MeasurementStationsServiceMock: MeasurementStationsServiceProtocol {
        
    func getStations(success: @escaping (StationList) -> Void, failure: @escaping (Error?) -> Void) {
        let url = "stations"
        let data = readJson(filename: url)
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.1) { // Async maker! :)
            if let jsonData = data.0, let result = try? JSONDecoder().decode(StationList.self, from: jsonData) {
                success(result)
            } else if let error = data.1 {
                failure(error)
            }
        }
    }
    
    func getMeasurement(station: Station, symbol: MeasurementSymbol, success: @escaping (MeasurementList) -> Void, failure: @escaping (Error?) -> Void) {
        let url = "measurements-\(station.no)-\(symbol.rawValue)"
        let data = readJson(filename: url)
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.1) { // Async maker! :)
            if let jsonData = data.0, let result = try? JSONDecoder().decode(MeasurementList.self, from: jsonData) {
                success(result)
            } else if let error = data.1 {
                failure(error)
            }
        }

    }
    
}

private extension MeasurementStationsServiceMock {
    
    private func readJson(filename: String) -> (Data?, NSError?) {
        do {
            if let file = Bundle.main.url(forResource: filename, withExtension: "json") {
                return try (Data(contentsOf: file), nil)
            } else {
                return (nil, NSError(domain: "File not found", code: 404, userInfo: nil))
            }
        } catch {
            return (nil, error as NSError)
        }
    }
    
}
