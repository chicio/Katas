//
//  InputPlayerParser.m
//  objective-c-tennis-game-test
//
//  Created by Fabrizio Duroni on 10.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <XCTest/XCTest.h>

#import "InputPlayerParser.h"

@interface InputPlayerParserTest : XCTestCase

@property (nonatomic) InputPlayerParser *inputPlayerParser;

@end

@implementation InputPlayerParserTest

- (void)setUp {
    self.inputPlayerParser = [InputPlayerParser new];
}

- (void)testParsePlayer1 {
    
    InputPlayer player = [self.inputPlayerParser parse:@"1"];
    
    XCTAssertEqual(player, Player1);
}

- (void)testParsePlayer2 {
    
    InputPlayer player = [self.inputPlayerParser parse:@"2"];
    
    XCTAssertEqual(player, Player2);
}

@end
