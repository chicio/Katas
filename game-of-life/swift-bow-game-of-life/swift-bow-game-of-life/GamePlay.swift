//
//  GamePlay.swift
//  swift-bow-game-of-life
//
//  Created by Fabrizio Duroni on 30.09.20.
//

import Foundation
import Bow

func getNeighboursValueUsing(row: Int, column: Int, matrix: Matrix) -> Int {
    getCellFrom(matrix: matrix, rowPosition: row, columnPosition: column).fold(
        { 0 },
        { (cell: CellStatus) in
            switch(cell) {
            case .Alive: return 1
            case .Dead: return 0
            }
        }
    )
}

func getCellFrom(matrix: Matrix, rowPosition: Int, columnPosition: Int) -> Option<CellStatus> {
    if matrix.indices.contains(rowPosition) && matrix[rowPosition].indices.contains(columnPosition) {
        return Option.some(matrix[rowPosition][columnPosition])
    }
    
    return Option.none()
}
