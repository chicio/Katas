import {
    Game,
    gameToPlayer1Score,
    gameToPlayer2Score,
    player1,
    player2,
    Score,
    ScoringPlayer,
    showScore
} from "./Data";
import {chain, map, of, Task} from "fp-ts/Task";
import {ask, puts} from "./Console";
import {fold, none, Option, some} from "fp-ts/Option";
import {pipe} from "fp-ts/pipeable";
import {gameCompleted, trackScoredPoint} from "./GamePlay";

export const welcome: Task<void> = puts("Welcome to the Tennis Game!")

export const playTennisGame: (game: Game) => Task<Game> = (game: Game) => pipe(
    readPlayer(),
    chain(player => trackScoredPoint(player, game)),
    chain(game => showGameScore(game)),
    chain( game => gameCompleted(game) ? of(game) : playTennisGame(game))
)

export const displayableGameScore: (game: Game) => string = (game: Game) => {
    const player1Score: Score = gameToPlayer1Score.get(game);
    const player2Score: Score = gameToPlayer2Score.get(game);

    if (player1Score._tag === 'Forty' && player2Score._tag === 'Forty') {
        return "Deuce"
    }

    if (player1Score._tag === 'Wins') {
        return "Player 1 wins"
    }

    if (player2Score._tag === 'Wins') {
        return "Player 2 wins"
    }

    return `Player 1 ${showScore.show(player1Score)} - Player 2 ${showScore.show(player2Score)}`
}

const showGameScore: (game: Game) => Task<Game> = (game: Game) => pipe(
    game,
    displayableGameScore,
    puts,
    map(() => game)
)

export const readPlayer: () => Task<ScoringPlayer> = () => pipe(
        ask("Which player will play (1 or 2)?"),
        map(parsePlayer),
        chain((player: Option<ScoringPlayer>) =>
            pipe(
                player,
                fold(
                    () => readPlayer(),
                    (validPlayer: ScoringPlayer) =>  of(validPlayer)
                )
            )
        )
    );

const parsePlayer: (input: string) => Option<ScoringPlayer> = (input: string) => {
    switch (input) {
        case "1":
            return some(player1())
        case "2":
            return some(player2())
        default:
            return none
    }
}
