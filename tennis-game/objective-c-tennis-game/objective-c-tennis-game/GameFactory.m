//
//  GameFactory.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import "GameFactory.h"

@implementation GameFactory

+ (Game *)make {
    return [[Game alloc] initWithPlayer1:[[Player alloc] initWithScore:Love] player2:[[Player alloc] initWithScore:Love]];
}

+ (Game *)makeUsingPlayer1Score: (Score)player1Score player2Score: (Score)player2Score {
    return  [[Game alloc] initWithPlayer1:[[Player alloc] initWithScore:player1Score] player2:[[Player alloc] initWithScore:player2Score]];
}

@end
