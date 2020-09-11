//
//  GameScorePrinter.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 10.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "Game.h"
#import "GameScoreStringConverter.h"
#import "Console.h"

NS_ASSUME_NONNULL_BEGIN

@interface GameScorePrinter : NSObject

- (id)initWithConsole: (Console *)console gameScoreStringConverter: (GameScoreStringConverter *)gameScoreStringConverter;

- (void)print: (Game *)game;

@end

NS_ASSUME_NONNULL_END
