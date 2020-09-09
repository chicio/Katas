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
#import "GameScoreCalculator.h"
#import "GameFactory.h"
#import "GameScoreStringConverter.h"

@interface TennisGameTest : XCTestCase

@end

@implementation TennisGameTest

- (void)testStart {
    id console = OCMClassMock([Console class]);
    id playerInputParser = OCMClassMock([PlayerInputParser class]);
    id gameScoreCalculator = OCMClassMock([GameScoreCalculator class]);
    id gameScoreConverter = OCMClassMock([GameScoreStringConverter class]);
    
    OCMStub([console read]).andReturn(@"1");
    OCMStub([playerInputParser parse:@"1"]).andReturn(Player1);
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
    OCMStub([gameScoreConverter convert:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Fifteen player2Score:Love]]])
             .andReturn(@"Player 1 Fifteen - Player 2 Love");
    OCMStub([gameScoreConverter convert:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Thirty player2Score:Love]]])
             .andReturn(@"Player 1 Thirty - Player 2 Love");
    OCMStub([gameScoreConverter convert:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Forty player2Score:Love]]])
             .andReturn(@"Player 1 Forty - Player 2 Love");
    OCMStub([gameScoreConverter convert:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Wins player2Score:Love]]])
             .andReturn(@"Player 1 wins");
    
    [[[TennisGame alloc] initWithConsole:console
                       playerInputParser:playerInputParser
                     gameScoreCalculator:gameScoreCalculator
                        gameScorePrinter:gameScoreConverter] start];
    
    OCMVerify([console put:@"Welcome to the Tennis Game!"]);
    OCMVerify([console put:@"Which player will play (1 or 2)?"]);
    OCMVerify([console read]);
    OCMVerify([playerInputParser parse:@"1"]);
    OCMVerify([gameScoreCalculator calculateFromCurrentGame:[OCMArg isEqual:[GameFactory make]] andInputPlayer:Player1]);
    OCMVerify([gameScoreCalculator calculateFromCurrentGame:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Fifteen player2Score:Love]] andInputPlayer:Player1]);
    OCMVerify([gameScoreCalculator calculateFromCurrentGame:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Thirty player2Score:Love]] andInputPlayer:Player1]);
    OCMVerify([gameScoreCalculator calculateFromCurrentGame:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Forty player2Score:Love]] andInputPlayer:Player1]);
    OCMVerify([gameScoreConverter convert:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Fifteen player2Score:Love]]]);
    OCMVerify([gameScoreConverter convert:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Thirty player2Score:Love]]]);
    OCMVerify([gameScoreConverter convert:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Forty player2Score:Love]]]);
    OCMVerify([gameScoreConverter convert:[OCMArg isEqual:[GameFactory makeUsingPlayer1Score:Wins player2Score:Love]]]);
    OCMVerify([console put:@"Player 1 Fifteen - Player 2 Love"]);
    OCMVerify([console put:@"Player 1 Thirty - Player 2 Love"]);
    OCMVerify([console put:@"Player 1 Forty - Player 2 Love"]);
    OCMVerify([console put:@"Player 1 wins"]);
}

@end
