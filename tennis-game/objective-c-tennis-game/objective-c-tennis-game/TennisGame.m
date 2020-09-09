//
//  TennisGame.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import "TennisGame.h"

@interface TennisGame ()
@property (nonatomic, strong) Console *console;
@property (nonatomic, strong) PlayerInputParser *playerInputParser;
@end

@implementation TennisGame
    
- (id)initWithConsole: (Console *)console playerInputParser: (PlayerInputParser *)playerInputParser {
    self = [super init];
    if (self) {
        self.console = console;
        self.playerInputParser = playerInputParser;
    }
    return self;
}

- (void)gameLoop {
    [self.console put:@"Welcome to the Tennis Game!"];
    
    [self.console put:@"Which player will play (1 or 2)?"];
    NSString *input = [self.console read];
    [self.playerInputParser parse:input];
}

@end
