//
//  GameInteraction.swift
//  swift-bow-tennis-game
//
//  Created by Fabrizio Duroni on 08.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

import Foundation
import Bow
import BowEffects

func welcome() -> IO<Never, Void> {
    return puts(message: "Welcome to the Tennis Game!")
}

func playTennisGame(game: Game) -> IO<Never, Game> {
    return readPlayer()
        .flatMap { (scoringPlayer: ScoringPLayer) in trackScoredPoint(scoringPlayer: scoringPlayer, game: game) }
        .flatMap { (updatedGame: Game) in showScoreFor(game: updatedGame) }
        .flatMap { (updatedGame: Game) in
            return completed(game: updatedGame) == true
            ? IO.invoke { updatedGame }
            : playTennisGame(game: updatedGame)
        }^
}

func showScoreFor(game: Game) -> IO<Never, Game> {
    return puts(message: displayableScoreFor(game: game)).map({ game.copy() })^
}

func readPlayer() -> IO<Never, ScoringPLayer> {
    return ask(question: "Which player will play (1 or 2)?")
        .map({ input in parsePlayer(input: input) })
        .flatMap({ (scoringPLayer: Option<ScoringPLayer>) in
            scoringPLayer.fold(
                { readPlayer() },
                { (validScoringPlayer: ScoringPLayer) in IO.invoke { validScoringPlayer } }
            )
        })^
}

private func parsePlayer(input: String) -> Option<ScoringPLayer> {
    switch (input) {
    case "1": return Option.some(ScoringPLayer.Player1)
    case "2": return Option.some(ScoringPLayer.Player2)
    default: return Option.none()
    }
}

private func displayableScoreFor(game: Game) -> String {
    let player1Score: Score = gameToPlayer1Score.get(game)
    let player2Score: Score = gameToPlayer2Score.get(game)
    
    switch (player1Score, player2Score) {
    case (.Forty, .Forty): return "Deuce"
    case(player1Score, player2Score) where player1Score == .Wins: return "Player 1 wins"
    case(player1Score, player2Score) where player2Score == .Wins: return "Player 2 wins"
    default:
        return "Player 1 \(show(score: player1Score)) - Player 2 \(show(score: player2Score))"
    }
}
