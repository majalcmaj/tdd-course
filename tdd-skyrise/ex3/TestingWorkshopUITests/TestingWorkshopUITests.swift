//
//  TestingWorkshopUITests.swift
//  TestingWorkshopUITests
//
//  Created by Konrad Roj on 09/04/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import XCTest

class TestingWorkshopUITests: XCTestCase {

    override func setUp() {
        let app = XCUIApplication()
        app.launchArguments = ["testMode"]
        app.launch()
    }

    func testShouldDisplayOneRowInMasterViewWhenAppFinishLaunching() {
        let table = XCUIApplication().tables.firstMatch
        XCTAssertEqual(table.cells.count, 1, "Number of cells is not equal 1 as expected.")
    }

}
