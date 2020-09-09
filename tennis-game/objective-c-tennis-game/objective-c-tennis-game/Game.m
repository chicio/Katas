//
//  Game.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import "Game.h"

@implementation Game

- (id)initWithPlayer1: (Player *)player1 player2: (Player *)player2 {
    self = [super init];
    if (self) {
        self.player1 = player1;
        self.player2 = player2;
    }
    return self;
}

- (BOOL)isEqual:(Game *)other {
    return [self.player1 isEqual:other.player1] && [self.player2 isEqual:other.player2];
}

- (BOOL)completed {
    return self.player1.score == Wins || self.player2.score == Wins;
}

@end
