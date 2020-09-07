import {readPlayer, showGameScore, welcome} from "./GameInteraction";
import {pipe} from "fp-ts/pipeable";
import {createGame, Game} from "./Data";
import {chain, of, Task} from "fp-ts/Task";
import {gameCompleted, trackScoredPoint} from "./GamePlay";

export const playTennisGame: (game: Game) => Task<Game> = (game: Game) => pipe(
    readPlayer(),
    chain(player => trackScoredPoint(player, game)),
    chain(game => showGameScore(game)),
    chain( game => gameCompleted(game) ? of(game) : playTennisGame(game))
)

export const tennisGame = () => pipe(
    welcome,
    chain(() => playTennisGame(createGame()))
)

export const main: () => void = () => {
    tennisGame()()
}

main()
