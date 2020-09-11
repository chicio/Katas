//
//  GameScorePrinter.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import "GameScoreStringConverter.h"

@implementation GameScoreStringConverter

- (NSString *)convert: (Game *)game {
    if ([self isDeuce: game]) {
        return @"Deuce";
    }
    
    if (game.player1.score == Wins) {
        return @"Player 1 wins";
    }
    
    if (game.player2.score == Wins) {
        return @"Player 2 wins";
    }
    
    return [NSString stringWithFormat:@"Player 1 %@ - Player 2 %@", [self convertScore:game.player1.score], [self convertScore:game.player2.score]];
}

- (Boolean)isDeuce: (Game *)game {
    return game.player1.score == Forty && game.player2.score == Forty;
}

- (NSString *)convertScore: (Score)score {
    switch (score) {
        case Love: return @"Love";
        case Fifteen: return @"Fifteen";
        case Thirty: return @"Thirty";
        case Forty: return @"Forty";
        case Advantage: return @"Advantage";
        case Wins: return @"Wins";
    }
}

@end
