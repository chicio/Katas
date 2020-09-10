//
//  TennisGame.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "Console.h"
#import "PlayerInputParser.h"
#import "GameScoreCalculator.h"
#import "GameScorePrinter.h"
#import "InputPlayerReader.h"

NS_ASSUME_NONNULL_BEGIN

@interface TennisGame : NSObject

- (id)initWithConsole: (Console *)console
         playerReader: (InputPlayerReader *)playerReader
  gameScoreCalculator: (GameScoreCalculator *)gameScoreCalculator
     gameScorePrinter: (GameScorePrinter *)gameScorePrinter;

- (void)start;

@end

NS_ASSUME_NONNULL_END
