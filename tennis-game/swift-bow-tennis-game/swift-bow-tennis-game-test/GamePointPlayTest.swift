//
//  GamePointPlayTest.swift
//  swift-bow-tennis-game-test
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

import XCTest

class GamePointPlayTest: XCTestCase {
    private var game: Game!

    override func setUpWithError() throws {
        game = createGame()
    }

    func testPlayer1ScoreFifteen() throws {
        let updatedGame = trackScorePointFor(scoringPlayer: .Player1, game: game)
        let gameScore = displayableScoreFor(game: updatedGame)
        
        XCTAssertEqual(gameScore, "Player 1 Fifteen - Player 2 Love")
    }
    
    func testPlayer1ScoreThirty() throws {
        let gameAfterFirstPlay = trackScorePointFor(scoringPlayer: .Player1, game: game)
        let gameAfterSecondPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterFirstPlay)
        let gameScore = displayableScoreFor(game: gameAfterSecondPlay)
        
        XCTAssertEqual(gameScore, "Player 1 Thirty - Player 2 Love")
    }
    
    func testPlayer1ScoreForty() throws {
        let gameAfterFirstPlay = trackScorePointFor(scoringPlayer: .Player1, game: game)
        let gameAfterSecondPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterFirstPlay)
        let gameAfterThirdPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterSecondPlay)
        let gameScore = displayableScoreFor(game: gameAfterThirdPlay)
        
        XCTAssertEqual(gameScore, "Player 1 Forty - Player 2 Love")
    }
    
    func testPlayer2ScoreFifteen() throws {
        let updatedGame = trackScorePointFor(scoringPlayer: .Player2, game: game)
        let gameScore = displayableScoreFor(game: updatedGame)
        
        XCTAssertEqual(gameScore, "Player 1 Love - Player 2 Fifteen")
    }
 
    func testPlayer2ScoreThirty() throws {
        let gameAfterFirstPlay = trackScorePointFor(scoringPlayer: .Player2, game: game)
        let gameAfterSecondPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterFirstPlay)
        let gameScore = displayableScoreFor(game: gameAfterSecondPlay)
        
        XCTAssertEqual(gameScore, "Player 1 Love - Player 2 Thirty")
    }
    
    func testPlayer2ScoreForty() throws {
        let gameAfterFirstPlay = trackScorePointFor(scoringPlayer: .Player2, game: game)
        let gameAfterSecondPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterFirstPlay)
        let gameAfterThirdPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterSecondPlay)
        let gameScore = displayableScoreFor(game: gameAfterThirdPlay)
        
        XCTAssertEqual(gameScore, "Player 1 Love - Player 2 Forty")
    }
    
    func testDeuce() throws {
        let gameAfterFirstPlay = trackScorePointFor(scoringPlayer: .Player2, game: game)
        let gameAfterSecondPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterFirstPlay)
        let gameAfterThirdPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterSecondPlay)
        let gameAfterForthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterThirdPlay)
        let gameAfterFifthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterForthPlay)
        let gameAfterSixthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterFifthPlay)
        let gameAfterSeventhPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterSixthPlay)
        let gameAfterEighthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterSeventhPlay)
        let gameScore = displayableScoreFor(game: gameAfterEighthPlay)
        
        XCTAssertEqual(gameScore, "Deuce")
    }
    
    func testPlayer1Wins() {
        let gameAfterFirstPlay = trackScorePointFor(scoringPlayer: .Player2, game: game)
        let gameAfterSecondPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterFirstPlay)
        let gameAfterThirdPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterSecondPlay)
        let gameAfterForthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterThirdPlay)
        let gameAfterFifthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterForthPlay)
        let gameAfterSixthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterFifthPlay)
        let gameScore = displayableScoreFor(game: gameAfterSixthPlay)
        
        XCTAssertEqual(gameScore, "Player 1 wins")
    }
    
    func testPlayer2Wins() {
        let gameAfterFirstPlay = trackScorePointFor(scoringPlayer: .Player1, game: game)
        let gameAfterSecondPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterFirstPlay)
        let gameAfterThirdPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterSecondPlay)
        let gameAfterForthPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterThirdPlay)
        let gameAfterFifthPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterForthPlay)
        let gameAfterSixthPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterFifthPlay)
        let gameScore = displayableScoreFor(game: gameAfterSixthPlay)
        
        XCTAssertEqual(gameScore, "Player 2 wins")
    }
    
    func testPlayer1Advantage() throws {
        let gameAfterFirstPlay = trackScorePointFor(scoringPlayer: .Player2, game: game)
        let gameAfterSecondPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterFirstPlay)
        let gameAfterThirdPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterSecondPlay)
        let gameAfterForthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterThirdPlay)
        let gameAfterFifthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterForthPlay)
        let gameAfterSixthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterFifthPlay)
        let gameAfterSeventhPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterSixthPlay)
        let gameAfterEighthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterSeventhPlay)
        let gameAfterNinthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterEighthPlay)
        let gameScore = displayableScoreFor(game: gameAfterNinthPlay)
        
        XCTAssertEqual(gameScore, "Player 1 Advantage - Player 2 Forty")
    }
    
    func testPlayer2Advantage() throws {
        let gameAfterFirstPlay = trackScorePointFor(scoringPlayer: .Player2, game: game)
        let gameAfterSecondPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterFirstPlay)
        let gameAfterThirdPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterSecondPlay)
        let gameAfterForthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterThirdPlay)
        let gameAfterFifthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterForthPlay)
        let gameAfterSixthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterFifthPlay)
        let gameAfterSeventhPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterSixthPlay)
        let gameAfterEighthPlay = trackScorePointFor(scoringPlayer: .Player1, game: gameAfterSeventhPlay)
        let gameAfterNinthPlay = trackScorePointFor(scoringPlayer: .Player2, game: gameAfterEighthPlay)
        let gameScore = displayableScoreFor(game: gameAfterNinthPlay)
        
        XCTAssertEqual(gameScore, "Player 1 Forty - Player 2 Advantage")
    }
}
