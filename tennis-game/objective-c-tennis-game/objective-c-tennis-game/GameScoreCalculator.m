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
        case Player1: return [self calculateFromCurrentGame:game
                                              scoringPlayer:game.player1
                                             opponentPlayer:game.player2
                                                gameFactory:^Game *(Score scoringPlayer, Score opponentPlayer) {
            return [GameFactory makeUsingPlayer1Score:scoringPlayer
                                         player2Score:opponentPlayer];
        }];
        case Player2: return [self calculateFromCurrentGame:game
                                              scoringPlayer:game.player2
                                             opponentPlayer:game.player1
                                                gameFactory:^Game *(Score scoringPlayer, Score opponentPlayer) {
            return [GameFactory makeUsingPlayer1Score:opponentPlayer
                                         player2Score:scoringPlayer];
        }];
        case UnknownPlayer: return game;
    }
}

- (Game *)calculateFromCurrentGame: (Game *)game scoringPlayer: (Player *)scoringPlayer opponentPlayer: (Player *)opponentPlayer gameFactory: (Game * (^)(Score, Score))factory {
    switch (scoringPlayer.score) {
        case Love: return factory(Fifteen, opponentPlayer.score);
        case Fifteen: return factory(Thirty, opponentPlayer.score);
        case Thirty: return factory(Forty, opponentPlayer.score);
        case Forty: return opponentPlayer.score == Forty
            ? factory(Advantage, Forty)
            : opponentPlayer.score == Advantage ? factory(Forty, Forty) : factory(Wins, opponentPlayer.score);
        case Advantage: return factory(Wins, opponentPlayer.score);
        case Wins: return factory(scoringPlayer.score, opponentPlayer.score);
    }
}

@end
