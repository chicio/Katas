//
//  GameScoreCalculator.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "Game.h"
#import "InputPlayer.h"

NS_ASSUME_NONNULL_BEGIN

@interface GameScoreCalculator : NSObject

- (Game *)calculateFromCurrentGame: (Game *)game andInputPlayer: (InputPlayer)inputPlayer;

@end

NS_ASSUME_NONNULL_END
