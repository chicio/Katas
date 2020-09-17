import {alive, CellStatus, dead, Matrix} from "./Data";
import {fold, none, Option, some} from "fp-ts/Option";
import {pipe} from "fp-ts/pipeable";
import {Task} from "fp-ts/Task";

export const calculateNextGeneration: (matrix: Matrix) => Task<Matrix> =
    (matrix: Matrix) => () => Promise.resolve(nextGeneration(matrix))

const nextGeneration: (currentGeneration: Matrix) => Matrix =
    (currentGeneration: Matrix) =>
        currentGeneration.map((row: CellStatus[], rowPosition: number) =>
            row.map((cell: CellStatus, columnPosition: number) =>
                getNextGenerationCellStatusFor(cell, rowPosition, columnPosition, currentGeneration)))

const getNextGenerationCellStatusFor: (cellStatus: CellStatus, row: number, column: number, matrix: Matrix) => CellStatus =
    (cellStatus: CellStatus, row: number, column: number, matrix: Matrix) => {
        const numberOfNeighboursFor = getNumberOfNeighboursFor(row, column, matrix)
        if ((numberOfNeighboursFor == 2 || numberOfNeighboursFor == 3) && cellStatus._tag === 'Alive') {
            return alive()
        }

        if (numberOfNeighboursFor == 3 && cellStatus._tag === 'Dead') {
            return alive()
        }

        return dead()
    }

const getNumberOfNeighboursFor: (row: number, column: number, matrix: Matrix) => number =
    (row: number, column: number, matrix: Matrix) =>
        getNeighboursValueUsing(row - 1, column - 1, matrix) +
        getNeighboursValueUsing(row - 1, column, matrix) +
        getNeighboursValueUsing(row - 1, column + 1, matrix) +
        getNeighboursValueUsing(row, column - 1, matrix) +
        getNeighboursValueUsing(row, column + 1, matrix) +
        getNeighboursValueUsing(row + 1, column - 1, matrix) +
        getNeighboursValueUsing(row + 1, column, matrix) +
        getNeighboursValueUsing(row + 1, column + 1, matrix)

const getNeighboursValueUsing: (row: number, column: number, matrix: Matrix) => number =
    (row: number, column: number, matrix: Matrix) => pipe(
        getCellFrom(matrix, row, column),
        fold(
            () => 0,
            (cell: CellStatus) => {
                switch (cell._tag) {
                    case "Alive":
                        return 1
                    case "Dead":
                        return 0
                }
            }
        )
    )

const getCellFrom: (matrix: Matrix, rowPosition: number, columnPosition: number) => Option<CellStatus> =
    (matrix: Matrix, rowPosition: number, columnPosition: number) =>
        isAValidCellPosition(matrix, rowPosition, columnPosition)
            ? some(matrix[rowPosition][columnPosition])
            : none

const isAValidCellPosition: (matrix: CellStatus[][], rowPosition: number, columnPosition: number) => boolean =
    (matrix: CellStatus[][], rowPosition: number, columnPosition: number) =>
        rowPosition >= 0 && matrix.length > rowPosition && columnPosition >= 0 && matrix[rowPosition].length > columnPosition;
