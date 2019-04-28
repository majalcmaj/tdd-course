//
//  DetailTableViewCell.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 10/01/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import UIKit

class DetailTableViewCell: UITableViewCell {
    
    lazy var nameLabel: UILabel = makeNameLabel()
    lazy var typeImageView: UIImageView = makeTypeImageView()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupUI()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupUI() {
        selectionStyle = .none
        contentView.addSubview(nameLabel)
        contentView.addSubview(typeImageView)
        
        nameLabel.leftAnchor.constraint(equalTo: leftAnchor, constant: 16.0).isActive = true
        nameLabel.topAnchor.constraint(greaterThanOrEqualTo: topAnchor, constant: 12.0).isActive = true
        nameLabel.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        nameLabel.bottomAnchor.constraint(lessThanOrEqualTo: bottomAnchor, constant: -12.0).isActive = true
        nameLabel.rightAnchor.constraint(equalTo: typeImageView.leftAnchor, constant: -8.0).isActive = true
        
        typeImageView.rightAnchor.constraint(equalTo: rightAnchor, constant: -16.0).isActive = true
        typeImageView.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        typeImageView.widthAnchor.constraint(equalToConstant: 30.0).isActive = true
        typeImageView.heightAnchor.constraint(equalToConstant: 30.0).isActive = true
    }
    
    func setupWithMeasurement(_ measurement: MeasurementList, symbol: MeasurementSymbol?) {
        let dateString = measurement.data?.first?.date.stringValue ?? ""
        nameLabel.text = measurement.message
            + "\n"
            + dateString
            + "\n"
            + ("\(measurement.data?.first?.value ?? 0.0)")
        
        typeImageView.image = UIImage(named: symbol?.rawValue ?? "")
    }
    
}

private extension DetailTableViewCell {
    
    func makeNameLabel() -> UILabel {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 16.0)
        label.textColor = UIColor.darkGray
        label.numberOfLines = 0
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }
    
    func makeTypeImageView() -> UIImageView {
        let imageView = UIImageView()
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }
    
}

