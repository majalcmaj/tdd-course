//
//  MasterViewModel.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 08/04/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import Foundation

class MasterViewModel {
    private let service: MeasurementStationsServiceProtocol
    var stations: StationList?
    
    init(service: MeasurementStationsServiceProtocol) {
        self.service = service
    }
    
    func fetchData(completion: @escaping () -> Void) {
        service.getStations(success: { [weak self] (list) in
            self?.stations = list
            completion()
        }) { (error) in
            print(error?.localizedDescription ?? "Unknown error")
        }
    }
    
}
