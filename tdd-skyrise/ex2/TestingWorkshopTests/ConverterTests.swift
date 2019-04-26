//
//  ConverterTests.swift
//  TestingWorkshopTests
//
//  Created by Krzysztof on 09/04/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import XCTest
@testable import TestingWorkshop
/*
 
 Exercise 2: World Directions
 
 Write Converter class with method which converts degrees to correct world direction
 
 class Converter {
    func convertDegreesToWorldDirection(degrees: Float) -> String {}
 }
 
 Requirements:
 Method should return:
 
 a) North when degrees are more than 315 and less than or equal 45
 b) East when degrees are more than 45 and less than or equal 135
 c) South when degrees are more than 135 and less than or equal 225
 d) West when degrees are more than 225 and less than or equal 315
 
 Tip: Remember about testing multiple inputs and edge cases
 
 */

class ConverterTests: XCTestCase {
    
    func testShouldReturnNorthWhenDegreesAreMoreThan315AndLessThanOrEqual45() {
        // GIVEN
        let expected = "North"
        let degrees: [Float] = [316, 340, 360, 0, 35, 45]
        
        performShouldReturnDirectionWhenGivenDegrees(expected: expected, degrees: degrees)

    }
    
    func testShouldReturnEastWhenDegreesAreMoreThan45AndLessThanOrEqual135() {
        // GIVEN
        let expected = "East"
        let degrees: [Float] = [46, 64, 90, 120, 134]
       
        performShouldReturnDirectionWhenGivenDegrees(expected: expected, degrees: degrees)
    }
    
    func testShouldReturnSouthWhenDegreesAreMoreThan135AndLessThanOrEqual225() {
        // GIVEN
        let expected = "South"
        let degrees: [Float] = [136, 150, 180, 200, 225]
        
        performShouldReturnDirectionWhenGivenDegrees(expected: expected, degrees: degrees)
    }
    
    func testShouldReturnSouthWhenDegreesAreMoreThan225AndLessThanOrEqual315() {
        // GIVEN
        let expected = "West"
        let degrees: [Float] = [226, 250, 270, 300, 315]
       
        performShouldReturnDirectionWhenGivenDegrees(expected: expected, degrees: degrees)
    }
    
    func performShouldReturnDirectionWhenGivenDegrees(expected: String, degrees: [Float]) {
        // GIVEN
        let converter = Converter()
        
        for degree in degrees {
            // WHEN
            let direction = converter.convertDegreesToWorldDirection(degrees: degree)
            // THEN
            XCTAssertEqual(direction, expected)
        }
    }
}
