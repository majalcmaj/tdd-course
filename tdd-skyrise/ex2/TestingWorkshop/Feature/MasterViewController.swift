//
//  MasterViewController.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 04/01/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import UIKit

class MasterViewController: UITableViewController {
    let viewModel: MasterViewModel
    
    init(viewModel: MasterViewModel) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupTableView()
        fetchData()
    }
    
    private func setupTableView() {
        tableView.register(MasterTableViewCell.self, forCellReuseIdentifier: "MasterTableViewCell")
        tableView.delegate = self
        tableView.dataSource = self
        tableView.rowHeight = UITableView.automaticDimension
        tableView.estimatedRowHeight = 60
    }
    
    private func fetchData() {
        viewModel.fetchData { [weak self] in
            DispatchQueue.main.async {
                self?.tableView.reloadData()
            }
        }
    }

}

extension MasterViewController {

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.stations?.data.count ?? 0
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MasterTableViewCell", for: indexPath) as! MasterTableViewCell
        
        if let station = viewModel.stations?.data[indexPath.row] {
            cell.setupWithStation(station)
        }
        
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let object = viewModel.stations?.data[indexPath.row]
        guard let station = object else { return }
        let detailViewModel = DetailViewModel(service: MeasurementStationsService(), station: station)
        let detailViewController = DetailViewController(viewModel: detailViewModel)
        self.navigationController?.pushViewController(detailViewController, animated: true)
    }

}

