//
//  Data.swift
//  swift-bow-tennis-game
//
//  Created by Fabrizio Duroni on 08.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

import Foundation
import Bow
import BowOptics

enum Score {
    case Love
    case Fifteen
    case Thirty
    case Forty
    case Advantage
    case Wins
}

func show(score: Score) -> String {
    return String(describing: score)
}

public enum ScoringPLayer {
    case Player1
    case Player2
}

public struct Player {
    let score: Score
}

extension Player {
    func copy(score: Score) -> Player {
        return Player(score: score)
    }
}

public struct Game {
    let player1: Player
    let player2: Player
}

public func createGame() -> Game {
    return Game(player1: Player(score: .Love), player2: Player(score: .Love))
}

extension Game {
    func copy(player1: Player? = nil, player2: Player? = nil) -> Game {
        return Game(player1: player1 ?? self.player1, player2: player2 ?? self.player2)
    }
}

private let playerScore = Lens<Player, Score>(
    get: { (player: Player) -> Score in player.score },
    set: { (player: Player, score: Score) -> Player in player.copy(score: score) }
)
private let gamePlayer1 = Lens<Game, Player>(
    get: { (game: Game) -> Player in game.player1 },
    set: { (game: Game, player: Player) -> Game in game.copy(player1: player)}
)
private let gamePlayer2 = Lens<Game, Player>(
    get: { (game: Game) -> Player in game.player2 },
    set: { (game: Game, player: Player) -> Game in game.copy(player2: player)}
)
let gameToPlayer1Score: Lens<Game, Score>  = gamePlayer1.compose(playerScore)
let gameToPlayer2Score: Lens<Game, Score> = gamePlayer2.compose(playerScore)
