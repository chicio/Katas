//
//  Player.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "Score.h"

NS_ASSUME_NONNULL_BEGIN

@interface Player : NSObject

@property (nonatomic, assign) Score score;

- (id)initWithScore: (Score)score;

@end

NS_ASSUME_NONNULL_END
