//
//  PlayerInputParser.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import "InputPlayerParser.h"

@implementation InputPlayerParser

- (InputPlayer)parse: (NSString *)input {
    if ([input isEqualToString:@"1"]) {
        return Player1;
    }
    
    if ([input isEqualToString:@"2"]) {
        return Player2;
    }
    
    return UnknownPlayer;
}

@end
