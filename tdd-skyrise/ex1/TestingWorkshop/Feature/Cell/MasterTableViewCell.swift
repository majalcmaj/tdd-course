//
//  MasterTableViewCell.swift
//  TestingWorkshop
//
//  Created by Konrad Roj on 10/01/2019.
//  Copyright © 2019 Skyrise. All rights reserved.
//

import UIKit

class MasterTableViewCell: UITableViewCell {
    
    lazy var nameLabel: UILabel = makeNameLabel()
    lazy var stackView: UIStackView = makeStackView()
    private var stackViewWidthConstraint: NSLayoutConstraint!
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupUI()
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupUI() {
        contentView.addSubview(nameLabel)
        contentView.addSubview(stackView)
        
        nameLabel.leftAnchor.constraint(equalTo: leftAnchor, constant: 16.0).isActive = true
        nameLabel.topAnchor.constraint(greaterThanOrEqualTo: topAnchor, constant: 12.0).isActive = true
        nameLabel.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        nameLabel.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -12.0).isActive = true
        nameLabel.rightAnchor.constraint(equalTo: stackView.leftAnchor, constant: -8.0).isActive = true
        
        stackView.rightAnchor.constraint(equalTo: rightAnchor, constant: -16.0).isActive = true
        stackView.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        stackViewWidthConstraint = stackView.widthAnchor.constraint(equalToConstant: 120.0)
        stackViewWidthConstraint.isActive = true
        stackView.heightAnchor.constraint(equalToConstant: 30.0).isActive = true
    }
    
    func setupWithStation(_ station: Station) {
        nameLabel.text = station.name
        
        for subview in stackView.arrangedSubviews {
            stackView.removeArrangedSubview(subview)
            subview.removeFromSuperview()
        }
        
        for symbolName in station.activeSymbols {
            let imageView = makeTypeImageView(imageName: symbolName.rawValue)
            stackView.addArrangedSubview(imageView)
            imageView.heightAnchor.constraint(equalToConstant: 30).isActive = true
        }
        
        let stackViewConstantValue = CGFloat((station.activeSymbols.count * 30) + (station.activeSymbols.count * 3))
        stackViewWidthConstraint.constant = stackViewConstantValue
        layoutIfNeeded()
    }
    
}

private extension MasterTableViewCell {
    
    func makeNameLabel() -> UILabel {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 16.0)
        label.textColor = UIColor.darkGray
        label.numberOfLines = 2
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }
    
    func makeTypeImageView(imageName: String) -> UIImageView {
        let imageView = UIImageView(image: UIImage(named: imageName))
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }
    
    func makeStackView() -> UIStackView {
        let stackView = UIStackView()
        stackView.alignment = .trailing
        stackView.distribution = .fillEqually
        stackView.axis = .horizontal
        stackView.spacing = 3.0
        stackView.translatesAutoresizingMaskIntoConstraints = false
        return stackView
    }

}
