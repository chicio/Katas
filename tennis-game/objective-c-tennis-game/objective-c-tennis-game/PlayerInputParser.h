//
//  PlayerInputParser.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef enum InputPlayer: NSUInteger {
    Player1,
    Player2,
} InputPlayer;

@interface PlayerInputParser : NSObject

- (InputPlayer)parse:(NSString *)input;

@end

NS_ASSUME_NONNULL_END
