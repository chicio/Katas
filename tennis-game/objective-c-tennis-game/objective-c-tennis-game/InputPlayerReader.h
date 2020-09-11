//
//  PlayerReader.h
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 10.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "InputPlayer.h"
#import "Console.h"
#import "InputPlayerParser.h"

NS_ASSUME_NONNULL_BEGIN

@interface InputPlayerReader : NSObject

- (id)initWithConsole: (Console *)console inputPlayerParser: (InputPlayerParser *)inputPlayerParser;

- (InputPlayer)readPlayer;

@end

NS_ASSUME_NONNULL_END
