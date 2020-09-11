//
//  GameScoreStringConverter.m
//  objective-c-tennis-game-test
//
//  Created by Fabrizio Duroni on 11.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <XCTest/XCTest.h>

#import "GameScoreStringConverter.h"
#import "GameFactory.h"

@interface GameScoreStringConverterTest : XCTestCase

@property (nonatomic) GameScoreStringConverter *gameScoreStringConverter;

@end

@implementation GameScoreStringConverterTest

- (void)setUp {
    self.gameScoreStringConverter = [GameScoreStringConverter new];
}

- (void)testGameScores {
    NSString *gameScore1 = [self.gameScoreStringConverter convert:[GameFactory makeUsingPlayer1Score:Fifteen player2Score:Fifteen]];
    NSString *gameScore2 = [self.gameScoreStringConverter convert:[GameFactory makeUsingPlayer1Score:Thirty player2Score:Fifteen]];
    NSString *gameScore3 = [self.gameScoreStringConverter convert:[GameFactory makeUsingPlayer1Score:Forty player2Score:Fifteen]];
    NSString *gameScore4 = [self.gameScoreStringConverter convert:[GameFactory makeUsingPlayer1Score:Forty player2Score:Advantage]];

    XCTAssertEqualObjects(gameScore1, @"Player 1 Fifteen - Player 2 Fifteen");
    XCTAssertEqualObjects(gameScore2, @"Player 1 Thirty - Player 2 Fifteen");
    XCTAssertEqualObjects(gameScore3, @"Player 1 Forty - Player 2 Fifteen");
    XCTAssertEqualObjects(gameScore4, @"Player 1 Forty - Player 2 Advantage");
}

- (void)testDeuce {
    NSString *gameScore = [self.gameScoreStringConverter convert:[GameFactory makeUsingPlayer1Score:Forty player2Score:Forty]];
    
    XCTAssertEqualObjects(gameScore, @"Deuce");
}

- (void)testPlayer1Wins {
    NSString *gameScore = [self.gameScoreStringConverter convert:[GameFactory makeUsingPlayer1Score:Wins player2Score:Forty]];
    
    XCTAssertEqualObjects(gameScore, @"Player 1 wins");
}

- (void)testPlayer2Wins {
    NSString *gameScore = [self.gameScoreStringConverter convert:[GameFactory makeUsingPlayer1Score:Forty player2Score:Wins]];
    
    XCTAssertEqualObjects(gameScore, @"Player 2 wins");
}

@end
