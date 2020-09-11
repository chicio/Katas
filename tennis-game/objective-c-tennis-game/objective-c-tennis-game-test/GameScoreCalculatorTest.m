//
//  GameScoreCalculatorTest.m
//  objective-c-tennis-game-test
//
//  Created by Fabrizio Duroni on 10.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

#import <XCTest/XCTest.h>

#import "GameFactory.h"
#import "GameScoreCalculator.h"

@interface GameScoreCalculatorTest : XCTestCase

@property (nonatomic) GameScoreCalculator *gameScoreCalculator;

@end

@implementation GameScoreCalculatorTest

- (void)setUp {
    self.gameScoreCalculator = [[GameScoreCalculator alloc] init];
}

- (void)testPlayer1ScoreFifteen {
    Game *game = [self.gameScoreCalculator calculateFromCurrentGame:[GameFactory make] andInputPlayer:Player1];
    
    XCTAssertEqualObjects(game, [GameFactory makeUsingPlayer1Score:Fifteen player2Score:Love]);
}

-(void)testPlayer1ScoreThirty {
    Game *game = [self.gameScoreCalculator calculateFromCurrentGame:[GameFactory makeUsingPlayer1Score:Fifteen player2Score:Love] andInputPlayer:Player1];
    
    XCTAssertEqualObjects(game, [GameFactory makeUsingPlayer1Score:Thirty player2Score:Love]);
}

-(void)testPlayer1ScoreForty {
    Game *game = [self.gameScoreCalculator calculateFromCurrentGame:[GameFactory makeUsingPlayer1Score:Thirty player2Score:Love] andInputPlayer:Player1];
    
    XCTAssertEqualObjects(game, [GameFactory makeUsingPlayer1Score:Forty player2Score:Love]);
}

- (void)testPlayer2ScoreFifteen {
    Game *game = [self.gameScoreCalculator calculateFromCurrentGame:[GameFactory make] andInputPlayer:Player2];
    
    XCTAssertEqualObjects(game, [GameFactory makeUsingPlayer1Score:Love player2Score:Fifteen]);
}

- (void)testPlayer2ScoreThirty {
    Game *game = [self.gameScoreCalculator calculateFromCurrentGame:[GameFactory makeUsingPlayer1Score:Love player2Score:Fifteen] andInputPlayer:Player2];
    
    XCTAssertEqualObjects(game, [GameFactory makeUsingPlayer1Score:Love player2Score:Thirty]);
}

- (void)testPlayer2ScoreForty {
    Game *game = [self.gameScoreCalculator calculateFromCurrentGame:[GameFactory makeUsingPlayer1Score:Love player2Score:Thirty] andInputPlayer:Player2];
    
    XCTAssertEqualObjects(game, [GameFactory makeUsingPlayer1Score:Love player2Score:Forty]);
}

- (void)testPlayer1Advantage {
    Game *game = [self.gameScoreCalculator calculateFromCurrentGame:[GameFactory makeUsingPlayer1Score:Forty player2Score:Forty] andInputPlayer:Player1];
    
    XCTAssertEqualObjects(game, [GameFactory makeUsingPlayer1Score:Advantage player2Score:Forty]);
}

- (void)testPlayer2Advantage {
    Game *game = [self.gameScoreCalculator calculateFromCurrentGame:[GameFactory makeUsingPlayer1Score:Forty player2Score:Forty] andInputPlayer:Player2];
    
    XCTAssertEqualObjects(game, [GameFactory makeUsingPlayer1Score:Forty player2Score:Advantage]);
}

- (void)testPlayer1Wins {
    Game *game = [self.gameScoreCalculator calculateFromCurrentGame:[GameFactory makeUsingPlayer1Score:Forty player2Score:Fifteen] andInputPlayer:Player1];
    
    XCTAssertEqualObjects(game, [GameFactory makeUsingPlayer1Score:Wins player2Score:Fifteen]);
}

- (void)testPlayer2Wins {
    Game *game = [self.gameScoreCalculator calculateFromCurrentGame:[GameFactory makeUsingPlayer1Score:Fifteen player2Score:Forty] andInputPlayer:Player2];
    
    XCTAssertEqualObjects(game, [GameFactory makeUsingPlayer1Score:Fifteen player2Score:Wins]);
}

- (void)testUnknownPlayer {
    Game *game = [self.gameScoreCalculator calculateFromCurrentGame:[GameFactory makeUsingPlayer1Score:Fifteen player2Score:Forty] andInputPlayer:UnknownPlayer];
    
    XCTAssertEqualObjects(game, [GameFactory makeUsingPlayer1Score:Fifteen player2Score:Forty]);
}

@end
