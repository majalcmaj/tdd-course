//
//  AppDelegate.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 04/01/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate, UISplitViewControllerDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        self.window = UIWindow(frame: UIScreen.main.bounds)
        
        let masterViewModel = MasterViewModel(service: Service.measurementStations)
        let initialViewController = UINavigationController(rootViewController: MasterViewController(viewModel: masterViewModel))
        
        self.window?.rootViewController = initialViewController
        self.window?.makeKeyAndVisible()
        
        return true
    }

}

