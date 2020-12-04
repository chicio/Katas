import {puts} from "./Console";
import {alive, dead, Matrix, showMatrix} from "./Data";

export const welcome: Promise<void> = puts("Welcome to the Conway's Game of Life!")

export const print: (matrix: Matrix) => Promise<Matrix> = (matrix: Matrix) => puts(showMatrix(matrix)).then(() => matrix)

export const readInitialMatrix: Promise<Matrix> = Promise.resolve([
    [dead(), alive(), dead()],
    [dead(), alive(), dead()],
    [dead(), alive(), dead()]
])
