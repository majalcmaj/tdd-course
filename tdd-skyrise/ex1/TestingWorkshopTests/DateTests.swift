//
//  DateTests.swift
//  TestingWorkshopTests
//
//  Created by Krzysztof on 09/04/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import XCTest
@testable import TestingWorkshop

/*
 Exercise 1: Date formatting
 
 Write new extension method for Date (inside Date+Additions.swift file) which returns new date string based on format argument.
 Use format for testing purpose: "dd/MM/yyyy"
 
 Requirements:
 1. stringValue(withFormat:) returns stringValue when format is Valid
 2. stringValue(withFormat:) returns nil when format isEmpty
 3. change format in app
*/

class DateTests: XCTestCase {
    private let expectedDateString = "15/05/2019"
    private var dateComponents: DateComponents {
        var dateComponents = DateComponents()
        dateComponents.day = 15
        dateComponents.month = 5
        dateComponents.year = 2019
        return dateComponents
    }
    
    // Date should return string value when format is valid
    func testShouldReturnStringValueWhenFormatIsValid() {
        // GIVEN
        let date = Calendar.current.date(from: dateComponents)!
        
        // WHEN
        let stringValue = date.stringValue(withFormat: "dd/MM/yyyy")
        
        // THEN
        XCTAssertEqual(stringValue, expectedDateString)
    }
    
    // Date should return nil when format is empty
    func testShouldReturnNilWhenFormatIsEmpty() {
        // Given
        let date = Calendar.current.date(from: dateComponents)!
        
        // When
        let returnedValue = date.stringValue(withFormat: "")
        
        // Then
        XCTAssertNil(returnedValue)
    }
}
