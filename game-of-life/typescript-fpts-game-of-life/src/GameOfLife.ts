import {Matrix} from "./Data";
import {pipe} from "fp-ts/pipeable";
import {print, readInitialMatrix, welcome} from "./GameInteraction";
import {calculateNextGeneration} from "./GamePlay";
import {chain, Task} from "fp-ts/Task";

export const gameOfLife: Task<Matrix> = pipe(
    welcome,
    chain(() => readInitialMatrix),
    chain((matrix: Matrix) => print(matrix)),
    chain((matrix: Matrix) => gameLoop(matrix))
)

const gameLoop: (matrix: Matrix) => Task<Matrix> = (matrix: Matrix) => pipe(
    calculateNextGeneration(matrix),
    chain((matrix: Matrix) => print(matrix)),
    chain((matrix: Matrix) => gameLoop(matrix))
)
