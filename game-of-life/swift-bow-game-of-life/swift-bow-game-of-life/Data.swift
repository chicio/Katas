//
//  Data.swift
//  swift-bow-game-of-life
//
//  Created by Fabrizio Duroni on 30.09.20.
//

import Foundation

enum CellStatus {
    case Alive
    case Dead
}

typealias Matrix = [[CellStatus]]

func show(matrix: Matrix) -> String {
    matrix.reduce("") { (matrixString: String, row: [CellStatus]) in
        row.reduce(matrixString) { (rowAsString: String, cell: CellStatus) in
            switch(cell) {
            case .Alive: return rowAsString + "* "
            case .Dead: return rowAsString + "X "
            }
        } + "\n"
    }
}
