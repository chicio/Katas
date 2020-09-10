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
#import "InputPlayerReader.h"
#import "GameScoreCalculator.h"
#import "GameFactory.h"
#import "GameScorePrinter.h"

@interface TennisGameTest : XCTestCase

@end

@implementation TennisGameTest

- (void)testStart {
    id console = OCMClassMock([Console class]);
    id playerReader = OCMClassMock([InputPlayerReader class]);
    id gameScoreCalculator = OCMClassMock([GameScoreCalculator class]);
    id gameScorePrinter = OCMClassMock([GameScorePrinter class]);
    
    OCMStub([playerReader readPlayer]).andReturn(Player1);
    OCMStub([gameScoreCalculator calculateFromCurrentGame:[OCMArg isEqual:[GameFactory make]]
                                           andInputPlayer:Player1])
        .andReturn([GameFactory makeUsingPlayer1Score:Fifteen player2Score:Love]);
    OCMStub([gameScoreCalculator calculateFromCurrentGame:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Fifteen player2Score:Love]]
                                           andInputPlayer:Player1])
        .andReturn([GameFactory makeUsingPlayer1Score:Thirty player2Score:Love]);
    OCMStub([gameScoreCalculator calculateFromCurrentGame:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Thirty player2Score:Love]]
                                           andInputPlayer:Player1])
        .andReturn([GameFactory makeUsingPlayer1Score:Forty player2Score:Love]);
    OCMStub([gameScoreCalculator calculateFromCurrentGame:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Forty player2Score:Love]]
                                           andInputPlayer:Player1])
        .andReturn([GameFactory makeUsingPlayer1Score:Wins player2Score:Love]);
    
    [[[TennisGame alloc] initWithConsole:console
                            playerReader:playerReader
                     gameScoreCalculator:gameScoreCalculator
                        gameScorePrinter:gameScorePrinter] start];
    
    OCMVerify([console put:@"Welcome to the Tennis Game!"]);
    OCMVerify([playerReader readPlayer]);
    OCMVerify([gameScoreCalculator calculateFromCurrentGame:[OCMArg isEqual:[GameFactory make]] andInputPlayer:Player1]);
    OCMVerify([gameScoreCalculator calculateFromCurrentGame:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Fifteen player2Score:Love]] andInputPlayer:Player1]);
    OCMVerify([gameScoreCalculator calculateFromCurrentGame:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Thirty player2Score:Love]] andInputPlayer:Player1]);
    OCMVerify([gameScoreCalculator calculateFromCurrentGame:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Forty player2Score:Love]] andInputPlayer:Player1]);
    OCMVerify([gameScorePrinter print:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Fifteen player2Score:Love]]]);
    OCMVerify([gameScorePrinter print:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Thirty player2Score:Love]]]);
    OCMVerify([gameScorePrinter print:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Forty player2Score:Love]]]);
    OCMVerify([gameScorePrinter print:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Wins player2Score:Love]]]);
}

@end
