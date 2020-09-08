import {tennisGame} from "../src/TennisGame";
jest.mock('readline');

describe('TennisGame', () => {
    it('game loop', async () => {
        console.log = jest.fn();

        await tennisGame()

        expect((console.log as jest.Mock).mock.calls[0][0]).toBe("Welcome to the Tennis Game!");
        expect((console.log as jest.Mock).mock.calls[1][0]).toBe( "Player 1 Fifteen - Player 2 Love");
        expect((console.log as jest.Mock).mock.calls[2][0]).toBe( "Player 1 Thirty - Player 2 Love");
        expect((console.log as jest.Mock).mock.calls[3][0]).toBe( "Player 1 Forty - Player 2 Love");
        expect((console.log as jest.Mock).mock.calls[4][0]).toBe( "Player 1 wins");

        await tennisGame()

        expect((console.log as jest.Mock).mock.calls[5][0]).toBe("Welcome to the Tennis Game!");
        expect((console.log as jest.Mock).mock.calls[6][0]).toBe( "Player 1 Love - Player 2 Fifteen");
        expect((console.log as jest.Mock).mock.calls[7][0]).toBe( "Player 1 Love - Player 2 Thirty");
        expect((console.log as jest.Mock).mock.calls[8][0]).toBe( "Player 1 Love - Player 2 Forty");
        expect((console.log as jest.Mock).mock.calls[9][0]).toBe( "Invalid player selected. Try again.");
        expect((console.log as jest.Mock).mock.calls[10][0]).toBe( "Player 2 wins");
    });
})
