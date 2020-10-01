//
//  GameOfLife.swift
//  swift-bow-game-of-life
//
//  Created by Fabrizio Duroni on 01/10/2020.
//

import Foundation
import BowEffects

func gameOfLife() -> IO<Never, Matrix> {
    return welcome()
        .flatMap({ readInitialMatrix() })
        .flatMap({ matrix in gameLoop(matrix: matrix)})^
}

private func gameLoop(matrix: Matrix) -> IO<Never, Matrix> {
    return print(matrix: matrix)
        .flatMap { matrix in nextGeneration(currentGeneration: matrix) }
        .flatMap { matrix in gameLoop(matrix: matrix) }^
}
