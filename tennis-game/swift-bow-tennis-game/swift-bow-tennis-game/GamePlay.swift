//
//  GamePlay.swift
//  swift-bow-tennis-game
//
//  Created by Fabrizio Duroni on 08.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

import Foundation
import BowOptics
import BowEffects

func trackScoredPoint(scoringPlayer: ScoringPLayer, game: Game) -> IO<Never, Game> {
    return IO.invoke { trackScorePointFor(scoringPlayer: scoringPlayer, game: game) }
}

func trackScorePointFor(scoringPlayer: ScoringPLayer, game: Game) -> Game {
    
    switch (scoringPlayer) {
    case .Player1: return update(game: game, scoringPlayerScore: gameToPlayer1Score, opponentPlayerScore: gameToPlayer2Score)
    case .Player2: return update(game: game, scoringPlayerScore: gameToPlayer2Score, opponentPlayerScore: gameToPlayer1Score)
    }
}

func completed(game: Game) -> Bool {
    return gameToPlayer1Score.get(game) == .Wins || gameToPlayer2Score.get(game) == .Wins
}

private func update(game: Game, scoringPlayerScore: Lens<Game, Score>, opponentPlayerScore: Lens<Game, Score>) -> Game {
    switch (scoringPlayerScore.get(game)) {
    case .Love: return scoringPlayerScore.set(game, .Fifteen)
    case .Fifteen: return scoringPlayerScore.set(game, .Thirty)
    case .Thirty: return scoringPlayerScore.set(game, .Forty)
    case .Forty:
        switch (opponentPlayerScore.get(game)) {
            case .Advantage: return opponentPlayerScore.set(game, .Forty)
            case .Forty: return scoringPlayerScore.set(game, .Advantage)
            default: return scoringPlayerScore.set(game, .Wins)
        }
    case .Advantage: return scoringPlayerScore.set(game, .Wins)
    case .Wins: return scoringPlayerScore.set(game, .Wins)
    }
}
