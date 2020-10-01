//
//  GamePlayTest.swift
//  GamePlayTest
//
//  Created by Fabrizio Duroni on 30.09.20.
//

import XCTest

class GamePlayTest: XCTestCase {
    func testASingleCellMatrixShouldDie() throws {
        let nextGenerationMatrix = try calculateNextGeneration(currentGeneration: [[.Alive]]).unsafeRunSync()
        
        XCTAssertEqual(nextGenerationMatrix, [[.Dead]])
    }
    
    func testA3x3MatrixWithASingleAliveCellInTheMiddle() throws {
        let nextGenerationMatrix = try calculateNextGeneration(currentGeneration: [
            [.Dead, .Dead, .Dead],
            [.Dead, .Alive, .Dead],
            [.Dead, .Dead, .Dead]
        ]).unsafeRunSync()
        
        XCTAssertEqual(
            nextGenerationMatrix,
            [[.Dead, .Dead, .Dead],
             [.Dead, .Dead, .Dead],
             [.Dead, .Dead, .Dead]]
        )
    }
}
