//
//  Game.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "Player.h"

NS_ASSUME_NONNULL_BEGIN

@interface Game : NSObject

@property (nonatomic, strong) Player *player1;
@property (nonatomic, strong) Player *player2;

- (id)initWithPlayer1: (Player *)player1 player2: (Player *)player2;

- (BOOL)completed;

@end

NS_ASSUME_NONNULL_END
