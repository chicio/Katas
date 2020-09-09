//
//  GameFactory.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "Game.h"
#import "Score.h"

NS_ASSUME_NONNULL_BEGIN

@interface GameFactory : NSObject

+ (Game *)make;

+ (Game *)makeUsingPlayer1Score: (Score)player1Score player2Score: (Score)player2Score;

@end

NS_ASSUME_NONNULL_END
