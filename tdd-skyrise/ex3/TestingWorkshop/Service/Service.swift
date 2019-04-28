//
//  Service.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 09/04/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import Foundation

/*
Unfortunately there is no way to inject code to application from UI test or XC tests.
MeasurementStationsServiceMock has to be in application target.
*/

final class Service {
    
    static var measurementStations: MeasurementStationsServiceProtocol {
        if isTestMode {
            return MeasurementStationsServiceMock()
        }
        return MeasurementStationsService()
    }
    
}

private extension Service {
    
    static var isTestMode: Bool {
        return ProcessInfo.processInfo.arguments.contains("testMode") ||
            ProcessInfo.processInfo.environment["XCTestConfigurationFilePath"] != nil
    }
    
}
