//
//  TennisGame.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import "TennisGame.h"
#import "GameFactory.h"

@interface TennisGame ()
@property (nonatomic, strong) Console *console;
@property (nonatomic, strong) InputPlayerReader *playerReader;
@property (nonatomic, strong) GameScoreCalculator *gameScoreCalculator;
@property (nonatomic, strong) GameScorePrinter *gameScorePrinter;
@end

@implementation TennisGame

- (id)initWithConsole: (Console *)console
         playerReader: (InputPlayerReader *)playerReader
  gameScoreCalculator: (GameScoreCalculator *)gameScoreCalculator
     gameScorePrinter: (GameScorePrinter *)gameScorePrinter {
    self = [super init];
    if (self) {
        self.console = console;
        self.playerReader = playerReader;
        self.gameScoreCalculator = gameScoreCalculator;
        self.gameScorePrinter = gameScorePrinter;
    }
    return self;
}

- (void)start {
    [self.console put:@"Welcome to the Tennis Game!"];
    [self gameLoop];
}

- (void)gameLoop {
    Game *game = [GameFactory make];
    while (![game completed]) {
        game = [self.gameScoreCalculator calculateFromCurrentGame:game andInputPlayer:[self.playerReader readPlayer]];
        [self.gameScorePrinter print:game];
    }
}

@end
