//
//  MeasurementStationsService.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 09/01/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import Foundation

class MeasurementStationsService: MeasurementStationsServiceProtocol {
    
    private let baseUrl = "https://pomiary.gdanskiewody.pl/rest/"
    
    func getStations(success: @escaping (StationList) -> Void, failure: @escaping (Error?) -> Void) {
        let url = URL(string: baseUrl + "stations")!
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        
        let task = URLSession.shared.dataTask(with: url) { (data, response, error) in
            guard let data = data else {
                failure(error)
                return
            }
            
            do {
                let decodedStations = try JSONDecoder().decode(StationList.self, from: data)
                DispatchQueue.main.async { success(decodedStations) }
            } catch let error {
                failure(error)
            }
        }
        
        task.resume()
    }
    
    func getMeasurement(station: Station, symbol: MeasurementSymbol, success: @escaping (MeasurementList) -> Void, failure: @escaping (Error?) -> Void) {
        let url = URL(string: baseUrl + "measurements/\(station.no)/\(symbol.rawValue)/\(Date().stringValue)")!
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        
        let task = URLSession.shared.dataTask(with: url) { (data, response, error) in
            guard let data = data else {
                failure(error)
                return
            }
            
            do {
                let decodedMeasurement = try JSONDecoder().decode(MeasurementList.self, from: data)
                DispatchQueue.main.async { success(decodedMeasurement) }
            } catch let error {
                failure(error)
            }
        }
        
        task.resume()
    }
    
}
