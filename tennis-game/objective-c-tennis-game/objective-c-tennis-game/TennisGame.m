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
@end

@implementation TennisGame
    
- (id)initWithConsole: (Console *)console {
    self = [super init];
    if (self) {
        self.console = console;
    }
    return self;
}

- (void)gameLoop {
    [self.console put:@"Welcome to the Tennis Game!"];
}

@end
