//
//  GameScorePrinterTest.m
//  objective-c-tennis-game-test
//
//  Created by Fabrizio Duroni on 11.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <XCTest/XCTest.h>
#import <OCMock/OCMock.h>

#import "GameScoreStringConverter.h"
#import "Console.h"
#import "GameScorePrinter.h"
#import "GameFactory.h"

@interface GameScorePrinterTest : XCTestCase

@property (nonatomic) id gameScoreStringConverter;
@property (nonatomic) id console;
@property (nonatomic) GameScorePrinter *gameScorePrinter;

@end

@implementation GameScorePrinterTest

- (void)setUp {
    self.console = OCMClassMock([Console class]);
    self.gameScoreStringConverter = OCMClassMock([GameScoreStringConverter class]);
    self.gameScorePrinter = [[GameScorePrinter alloc] initWithConsole: self.console gameScoreStringConverter: self.gameScoreStringConverter];
}

- (void)testPrint {
    Game *game = [GameFactory make];
    OCMStub([self.gameScoreStringConverter convert:game]).andReturn(@"Score");
    
    [self.gameScorePrinter print:game];
    
    OCMVerify([self.console put:@"Score"]);
}


@end
