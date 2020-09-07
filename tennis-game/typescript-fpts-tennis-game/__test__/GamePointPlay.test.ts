import {trackScorePointForPlayer} from "../src/GamePlay";
import {createGame, Game, player1, player2} from "../src/Data";
import {displayableGameScore} from "../src/GameInteraction";
import {pipe} from "fp-ts/pipeable";

describe('GamePointPlay', () => {
    const trackScorePlayer1 = (game: Game) => trackScorePointForPlayer(player1(), game)
    const trackScorePlayer2 = (game: Game) => trackScorePointForPlayer(player2(), game)

    describe('player 1',  () => {
        it('score fifteen', () => {
            const gameScore = pipe(
                createGame(),
                trackScorePlayer1,
                displayableGameScore
            )

            expect(gameScore).toEqual("Player 1 Fifteen - Player 2 Love")
        });

        it('score thirty', () => {
            const gameScore = pipe(
                createGame(),
                trackScorePlayer1,
                trackScorePlayer1,
                displayableGameScore
            )

            expect(gameScore).toEqual("Player 1 Thirty - Player 2 Love")
        });

        it('score forty', () => {
            const gameScore = pipe(
                createGame(),
                trackScorePlayer1,
                trackScorePlayer1,
                trackScorePlayer1,
                displayableGameScore
            )
            expect(gameScore).toEqual("Player 1 Forty - Player 2 Love")
        });

        it('wins', () => {
            const gameScore = pipe(
                createGame(),
                trackScorePlayer1,
                trackScorePlayer1,
                trackScorePlayer2,
                trackScorePlayer2,
                trackScorePlayer1,
                trackScorePlayer1,
                displayableGameScore
            )
            expect(gameScore).toEqual("Player 1 wins")
        });

        it('advantage', () => {
            const gameScore = pipe(
                createGame(),
                trackScorePlayer1,
                trackScorePlayer2,
                trackScorePlayer1,
                trackScorePlayer2,
                trackScorePlayer1,
                trackScorePlayer2,
                trackScorePlayer1,
                displayableGameScore
            )
            expect(gameScore).toEqual("Player 1 Advantage - Player 2 Forty")
        });
    })

    describe('player 2',  () => {
        it('score fifteen', () => {
            const gameScore = pipe(
                createGame(),
                trackScorePlayer2,
                displayableGameScore
            )

            expect(gameScore).toEqual("Player 1 Love - Player 2 Fifteen")
        });

        it('score thirty', () => {
            const gameScore = pipe(
                createGame(),
                trackScorePlayer2,
                trackScorePlayer2,
                displayableGameScore
            )

            expect(gameScore).toEqual("Player 1 Love - Player 2 Thirty")
        });

        it('score forty', () => {
            const gameScore = pipe(
                createGame(),
                trackScorePlayer2,
                trackScorePlayer2,
                trackScorePlayer2,
                displayableGameScore
            )

            expect(gameScore).toEqual("Player 1 Love - Player 2 Forty")
        });


        it('wins', () => {
            const gameScore = pipe(
                createGame(),
                trackScorePlayer2,
                trackScorePlayer2,
                trackScorePlayer1,
                trackScorePlayer1,
                trackScorePlayer2,
                trackScorePlayer2,
                displayableGameScore
            )
            expect(gameScore).toEqual("Player 2 wins")
        });

        it('advantage', () => {
            const gameScore = pipe(
                createGame(),
                trackScorePlayer1,
                trackScorePlayer2,
                trackScorePlayer1,
                trackScorePlayer2,
                trackScorePlayer1,
                trackScorePlayer2,
                trackScorePlayer2,
                displayableGameScore
            )
            expect(gameScore).toEqual("Player 1 Forty - Player 2 Advantage")
        });
    })

    it('Deuce', () => {
        const gameScore = pipe(
            createGame(),
            trackScorePlayer1,
            trackScorePlayer1,
            trackScorePlayer1,
            trackScorePlayer2,
            trackScorePlayer2,
            trackScorePlayer2,
            trackScorePlayer1,
            trackScorePlayer2,
            displayableGameScore
        );

        expect(gameScore).toEqual("Deuce")
    });
});
