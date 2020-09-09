//
//  TennisGame.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Console.h"

NS_ASSUME_NONNULL_BEGIN

@interface TennisGame : NSObject

- (id)initWithConsole: (Console *)console;
- (void)gameLoop;

@end

NS_ASSUME_NONNULL_END
