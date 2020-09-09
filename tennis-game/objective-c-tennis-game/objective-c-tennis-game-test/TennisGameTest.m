//
//  TennisGameTest.m
//  TennisGameTest
//
//  Created by Fabrizio Duroni on 09.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <XCTest/XCTest.h>
#import <OCMock/OCMock.h>

#import "TennisGame.h"
#import "Console.h"
#import "PlayerInputParser.h"

@interface TennisGameTest : XCTestCase

@end

@implementation TennisGameTest

- (void)testGameLoop {
    id console = OCMClassMock([Console class]);
    id playerInputParser = OCMClassMock([PlayerInputParser class]);
    
    OCMStub([console read]).andReturn(@"1");
    
    [[[TennisGame alloc] initWithConsole:console playerInputParser:playerInputParser] gameLoop];
    
    OCMVerify([console put:@"Welcome to the Tennis Game!"]);
    OCMVerify([console put:@"Which player will play (1 or 2)?"]);
    OCMVerify([console read]);
    OCMVerify([playerInputParser parse:@"1"]);
    
}
       
@end
