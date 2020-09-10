//
//  GameScoreCalculator.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import "GameScoreCalculator.h"
#import "GameFactory.h"

@implementation GameScoreCalculator

- (Game *)calculateFromCurrentGame: (Game *)game andInputPlayer: (InputPlayer)inputPlayer {
    switch (inputPlayer) {
        case Player1:
            switch (game.player1.score) {
                case Love: return [GameFactory makeUsingPlayer1Score:Fifteen player2Score:Love];
                case Fifteen: return [GameFactory makeUsingPlayer1Score:Thirty player2Score:Love];
                case Thirty: return [GameFactory makeUsingPlayer1Score:Forty player2Score:Love];
                case Forty: return game.player2.score == Forty
                    ? [GameFactory makeUsingPlayer1Score:Advantage player2Score:Forty]
                    : [GameFactory makeUsingPlayer1Score:Wins player2Score:game.player2.score];
                default: return [GameFactory makeUsingPlayer1Score:Fifteen player2Score:Love];
            }
            break;
        case Player2:
            switch (game.player2.score) {
                case Love: return [GameFactory makeUsingPlayer1Score:Love player2Score:Fifteen];
                case Fifteen: return [GameFactory makeUsingPlayer1Score:Love player2Score:Thirty];
                case Thirty: return [GameFactory makeUsingPlayer1Score:Love player2Score:Forty];
                case Forty: return game.player1.score == Forty
                    ? [GameFactory makeUsingPlayer1Score:Forty player2Score:Advantage]
                    : [GameFactory makeUsingPlayer1Score:game.player1.score player2Score:Wins];
                default: return [GameFactory makeUsingPlayer1Score:Fifteen player2Score:Love];
            }
            break;
    }
}

@end
