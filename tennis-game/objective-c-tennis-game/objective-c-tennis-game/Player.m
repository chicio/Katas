//
//  Player.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import "Player.h"

@implementation Player

- (id)initWithScore: (Score) score {
    self = [super init];
    if (self) {
        self.score = score;
    }
    return self;
}

- (BOOL)isEqual:(Player *)other {
    return self.score == other.score;
}

@end
