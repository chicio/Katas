//
//  GameScorePrinter.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 10.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import "GameScorePrinter.h"

@interface GameScorePrinter()

@property (nonatomic, strong) Console *console;
@property (nonatomic, strong) GameScoreStringConverter *gameScoreStringConverter;

@end

@implementation GameScorePrinter

- (id)initWithConsole: (Console *)console gameScoreStringConverter: (GameScoreStringConverter *)gameScoreStringConverter {
    self = [super init];
    if (self) {
        self.console = console;
        self.gameScoreStringConverter = gameScoreStringConverter;
    }
    return self;
}

- (void)print: (Game *)game {
    NSString *gameScore = [self.gameScoreStringConverter convert:game];
    [self.console put:gameScore];
}

@end
