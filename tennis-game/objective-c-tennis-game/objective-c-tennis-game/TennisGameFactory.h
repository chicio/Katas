//
//  TennisGameFactory.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 11.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "TennisGame.h"

NS_ASSUME_NONNULL_BEGIN

@interface TennisGameFactory : NSObject

+ (TennisGame *)make;

@end

NS_ASSUME_NONNULL_END
