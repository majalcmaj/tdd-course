//
//  DetailViewController.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 04/01/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import UIKit

class DetailViewController: UITableViewController {
    let viewModel: DetailViewModel
    
    init(viewModel: DetailViewModel) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        setupTableView()
        
        for symbol in viewModel.station.activeSymbols {
            viewModel.getMeasurement(symbol: symbol) { [weak self] in
                DispatchQueue.main.async {
                    self?.tableView.reloadData()
                }
            }
        }
    }
    
    func setupTableView() {
        view.backgroundColor = .white
        tableView.register(DetailTableViewCell.self, forCellReuseIdentifier: "DetailTableViewCell")
        tableView.delegate = self
        tableView.dataSource = self
        tableView.rowHeight = 120
    }

}

extension DetailViewController {
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.measurements.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "DetailTableViewCell", for: indexPath) as! DetailTableViewCell
        let model = viewModel.measurements[indexPath.row]
        cell.setupWithMeasurement(model)
        return cell
    }
    
}

