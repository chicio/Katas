import {puts} from "./Console";
import {alive, dead, Matrix, showMatrix} from "./Data";
import {pipe} from "fp-ts/pipeable";
import {map, Task} from "fp-ts/Task";

export const welcome: Task<void> = puts("Welcome to the Conway's Game of Life!")

export const print: (matrix: Matrix) => Task<Matrix> = (matrix: Matrix) => pipe(
    puts(showMatrix.show(matrix)),
    map(() => matrix)
)

export const readInitialMatrix: Task<Matrix> = () => Promise.resolve([
    [dead(), alive(), dead()],
    [dead(), alive(), dead()],
    [dead(), alive(), dead()]
])
