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

@interface TennisGameTest : XCTestCase

@end

@implementation TennisGameTest

- (void)testGameLoop {
    id console = OCMClassMock([Console class]);
    
    [[[TennisGame alloc] initWithConsole:console] gameLoop];
    
    OCMVerify([console put:@"Welcome to the Tennis Game!"]);
}
       
@end
