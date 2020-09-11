//
//  PlayerReader.m
//  objective-c-tennis-game
//
//  Created by Fabrizio Duroni on 10.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import "InputPlayerReader.h"

@interface InputPlayerReader ()

@property(nonatomic, strong) Console *console;
@property(nonatomic, strong) InputPlayerParser *inputPlayerParser;

@end

@implementation InputPlayerReader

- (id)initWithConsole: (Console *)console inputPlayerParser: (InputPlayerParser *)inputPlayerParser {
    self = [super init];
    if (self) {
        self.console = console;
        self.inputPlayerParser = inputPlayerParser;
    }
    return self;
}

- (InputPlayer)readPlayer {
    [self.console put:@"Which player will play (1 or 2)?"];
    return [self.inputPlayerParser parse: [self.console read]];
}

@end
