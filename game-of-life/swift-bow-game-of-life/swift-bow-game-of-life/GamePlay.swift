//
//  GamePlay.swift
//  swift-bow-game-of-life
//
//  Created by Fabrizio Duroni on 30.09.20.
//

import Foundation
import Bow

func nextGeneration(currentGeneration: Matrix) -> Matrix {
    return currentGeneration.enumerated().map { (rowPosition, row) in
        row.enumerated().map { (columnPosition, cell) in
            getNextGenerationCellStatusFor(cellStatus: cell, row: rowPosition, column: columnPosition, matrix: currentGeneration)
        }
    }
}

private func getNextGenerationCellStatusFor(cellStatus: CellStatus, row: Int, column: Int, matrix: Matrix) -> CellStatus {
    let numberOfNeighboursFor = getNumberOfNeighboursFor(row: row, column: column, matrix: matrix)
    
    if ((numberOfNeighboursFor == 2 || numberOfNeighboursFor == 3) && cellStatus == .Alive) {
        return .Alive
    }
    
    if ((numberOfNeighboursFor == 3) && cellStatus == .Dead) {
        return .Alive
    }
    
    return .Dead
}

private func getNumberOfNeighboursFor(row: Int, column: Int, matrix: Matrix) -> Int {
    return getNeighboursValueUsing(row: row - 1, column: column - 1, matrix: matrix) +
        getNeighboursValueUsing(row: row - 1, column: column, matrix: matrix) +
        getNeighboursValueUsing(row: row - 1, column: column + 1, matrix: matrix) +
        getNeighboursValueUsing(row: row, column: column - 1, matrix: matrix) +
        getNeighboursValueUsing(row: row, column: column + 1, matrix: matrix) +
        getNeighboursValueUsing(row: row + 1, column: column - 1, matrix: matrix) +
        getNeighboursValueUsing(row: row + 1, column: column, matrix: matrix) +
        getNeighboursValueUsing(row: row + 1, column: column + 1, matrix: matrix)
}

private func getNeighboursValueUsing(row: Int, column: Int, matrix: Matrix) -> Int {
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

private func getCellFrom(matrix: Matrix, rowPosition: Int, columnPosition: Int) -> Option<CellStatus> {
    if matrix.indices.contains(rowPosition) && matrix[rowPosition].indices.contains(columnPosition) {
        return Option.some(matrix[rowPosition][columnPosition])
    }
    
    return Option.none()
}
