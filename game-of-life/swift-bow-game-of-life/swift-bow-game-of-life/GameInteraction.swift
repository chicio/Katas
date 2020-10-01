//
//  GameInteraction.swift
//  swift-bow-game-of-life
//
//  Created by Fabrizio Duroni on 01.10.20.
//

import Foundation
import BowEffects

func welcome() -> IO<Never, Void> {
    return puts(message: "Welcome to the Tennis Game!")
}

func readInitialMatrix() -> IO<Never, Matrix> {
    return IO.invoke {
        parse(input: try! String(contentsOfFile: Bundle.main.path(forResource: "6x6-toad", ofType: "csv")!))
    }
}

func parse(input: String) -> Matrix {
    return input
        .split(separator: "\n")
        .map { line in
            return line
                .split(separator: ",")
                .map { element in
                    switch(element) {
                    case "Dead": return .Dead
                    default: return .Alive
                    }
                }
        }
        
}

func print(matrix: Matrix) -> IO<Never, Matrix> {
    return puts(message: show(matrix: matrix)).map { matrix }^
}

