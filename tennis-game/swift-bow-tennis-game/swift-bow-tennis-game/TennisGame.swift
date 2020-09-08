//
//  TennisGame.swift
//  swift-bow-tennis-game
//
//  Created by Fabrizio Duroni on 08.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

import Foundation
import BowEffects

func tennisGame() -> IO<Never, Game> {
    return welcome().flatMap { playTennisGame(game: createGame()) }^
}
