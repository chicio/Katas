import {playTennisGame, welcome} from "./GameInteraction";
import {pipe} from "fp-ts/pipeable";
import {createGame, Game} from "./Data";
import {chain, Task} from "fp-ts/Task";

export const tennisGame: Task<Game> = pipe(
    welcome,
    chain(() => playTennisGame(createGame()))
)

