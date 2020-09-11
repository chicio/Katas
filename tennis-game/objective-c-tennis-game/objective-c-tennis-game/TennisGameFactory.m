//
//  TennisGameFactory.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 11.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import "TennisGameFactory.h"
#import "Console.h"
#import "InputPlayerReader.h"
#import "GameScoreCalculator.h"
#import "GameScorePrinter.h"

@implementation TennisGameFactory

+ (TennisGame *)make {
    Console *console = [Console new];
    InputPlayerReader *playerReader = [[InputPlayerReader alloc] initWithConsole:console inputPlayerParser:[InputPlayerParser new]];
    GameScoreCalculator *gameScoreCalculator = [GameScoreCalculator new];
    GameScorePrinter *gameScorePrinter = [[GameScorePrinter alloc] initWithConsole:console gameScoreStringConverter:[GameScoreStringConverter new]];
    return [[TennisGame alloc] initWithConsole:console playerReader:playerReader gameScoreCalculator:gameScoreCalculator gameScorePrinter:gameScorePrinter];
}

@end
