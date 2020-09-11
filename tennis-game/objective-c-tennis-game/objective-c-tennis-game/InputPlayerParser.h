//
//  PlayerInputParser.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "InputPlayer.h"

NS_ASSUME_NONNULL_BEGIN

@interface InputPlayerParser : NSObject

- (InputPlayer)parse:(NSString *)input;

@end

NS_ASSUME_NONNULL_END
