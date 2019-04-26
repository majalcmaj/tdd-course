//
//  TestingWorkshopTests.swift
//  TestingWorkshopTests
//
//  Created by Konrad Roj on 04/01/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import XCTest
@testable import TestingWorkshop

/*
 
 Test III - Async expectations and testing mocked service.
 
 What to do?
 1. Create "testShouldSuccesfullyReturnMeasurementListWhenRequestEntityIsValid" test case.
 2. The test will use async method with Success (MeasurementList entity) and Failure (NSError entity) closures. Think about "THEN" assert requirements.
 3. Write THEN cases for Success and Failure closures.
 4. For WHEN use the "Service.measurementStations.getMeasurement" method. Fill closures with THEN cases.
 5. Write GIVEN station and symbol entities. Be sure to set proper values :)
 6. Run. Guess why test has succeed.
 7. Use "expectation", ".fulfill()" and "waitForExpectations". No idea what's that? Ask!
 8. Create "testShouldFailWithNotFoundErrorWhenRequestEntityIsNotValid" for not existing stations.
 
 */

class MeasurementStationsServiceMockTests: XCTestCase {
    
    // Check is json response properly decoded by the service.
    func testShouldSuccesfullyReturnMeasurementListWhenRequestEntityIsValid() {
        // GIVEN
        let station = Station(no: 666, name: "", active: true, rain: false, water: true, winddir: false, windlevel: false)
        let symbol = MeasurementSymbol.water
        
        let isoDate = "2019-04-08T04:00:00+0000"
        
        let dateFormatter = ISO8601DateFormatter()
        let date = dateFormatter.date(from:isoDate)!
        
        let expectations = expectation(description: "AsyncExp")
        
        // WHEN
        Service.measurementStations.getMeasurement(station: station, symbol: symbol, success:
            { (response ) in
                // THEN
                XCTAssertEqual(response.status, "success")
                XCTAssertEqual(response.message, "Wartości opadu")
                XCTAssertEqual(response.data?.count, 6)
                XCTAssertEqual(response.data?[0].date, date)
                XCTAssertEqual(response.data?[0].value, 0.122)
                
                expectations.fulfill()
        })
        {(error) in
            //THEN
            XCTFail("Error should not be present")
            expectations.fulfill()
        }
        
        waitForExpectations(timeout: 1.0, handler: nil)
    }
    
    // Check do service respond with error for not existing stations.
    func testShouldFailWithNotFoundErrorWhenRequestEntityIsNotValid() {
        // GIVEN
        let station = Station(no: 333, name: "", active: true, rain: false, water: true, winddir: false, windlevel: false)
        let symbol = MeasurementSymbol.water
        
        let expectations = expectation(description: "AsyncExp")
        
        // WHEN
        Service.measurementStations.getMeasurement(station: station, symbol: symbol, success:
            { (response ) in
                // THEN
                XCTFail("Response should not be created.")
                expectations.fulfill()
        })
        {(error) in
            //THEN
            XCTAssertNotNil(error)
            expectations.fulfill()
        }
        
        waitForExpectations(timeout: 1.0, handler: nil)
    }
    
}
