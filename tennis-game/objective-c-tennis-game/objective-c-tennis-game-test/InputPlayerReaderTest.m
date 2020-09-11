//
//  InputPlayerReaderTest.m
//  objective-c-tennis-game-test
//
//  Created by Fabrizio Duroni on 11.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <XCTest/XCTest.h>
#import <OCMock/OCMock.h>

#import "InputPlayerReader.h"
#import "Console.h"
#import "InputPlayerParser.h"

@interface InputPlayerReaderTest : XCTestCase

@property(nonatomic) id console;
@property(nonatomic) id inputPlayerParser;
@property(nonatomic) InputPlayerReader *inputPlayerReader;

@end

@implementation InputPlayerReaderTest

- (void)setUp {
    self.console = OCMClassMock([Console class]);
    self.inputPlayerParser = OCMClassMock([InputPlayerParser class]);
    self.inputPlayerReader = [[InputPlayerReader alloc] initWithConsole: self.console inputPlayerParser: self.inputPlayerParser];
}

- (void)testReadPlayer {
    OCMStub([self.console read]).andReturn(@"1");
    OCMStub([self.inputPlayerParser parse: @"1"]).andReturn(Player1);
    
    InputPlayer player = [self.inputPlayerReader readPlayer];
    
    XCTAssertEqual(player, Player1);
    OCMVerify([self.console put:@"Which player will play (1 or 2)?"]);
    OCMVerify([self.console read]);
    OCMVerify([self.inputPlayerParser parse:@"1"]);
}

@end
