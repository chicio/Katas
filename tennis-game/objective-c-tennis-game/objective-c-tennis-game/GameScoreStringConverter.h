//
//  GameScorePrinter.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "Game.h"

NS_ASSUME_NONNULL_BEGIN

@interface GameScoreStringConverter : NSObject

- (NSString *)convert: (Game *)game;

@end

NS_ASSUME_NONNULL_END
