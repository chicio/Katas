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
@property (nonatomic, strong) PlayerInputParser *playerInputParser;
@property (nonatomic, strong) GameScoreCalculator *gameScoreCalculator;
@property (nonatomic, strong) GameScoreStringConverter *gameScoreConverter;
@end

@implementation TennisGame

- (id)initWithConsole: (Console *)console
    playerInputParser: (PlayerInputParser *)playerInputParser
  gameScoreCalculator: (GameScoreCalculator *)gameScoreCalculator
     gameScorePrinter: (GameScoreStringConverter *)gameScorePrinter {
    self = [super init];
    if (self) {
        self.console = console;
        self.playerInputParser = playerInputParser;
        self.gameScoreCalculator = gameScoreCalculator;
        self.gameScoreConverter = gameScorePrinter;
    }
    return self;
}

- (void)start {
    [self.console put:@"Welcome to the Tennis Game!"];
    [self gameLoop:[GameFactory make]];
}

- (void)gameLoop: (Game *)game {
    while (![game completed]) {
        [self.console put:@"Which player will play (1 or 2)?"];
        game = [self.gameScoreCalculator calculateFromCurrentGame:game
                                                   andInputPlayer:[self.playerInputParser parse:[self.console read]]];
        [self.console put: [self.gameScoreConverter convert:game]];
    }
}

@end
