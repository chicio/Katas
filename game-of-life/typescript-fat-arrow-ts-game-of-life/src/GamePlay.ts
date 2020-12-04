import {alive, CellStatus, dead, Matrix} from "./Data";
import {Maybe, maybe, none} from 'fat-arrow-ts';

export const calculateNextGeneration: (matrix: Matrix) => Promise<Matrix> =
    (matrix: Matrix) => Promise.resolve(nextGeneration(matrix))

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
    (row: number, column: number, matrix: Matrix) =>
        getCellFrom(matrix, row, column).fold(
            () => 0,
            (cell: CellStatus) => {
                switch (cell._tag) {
                    case "Alive":
                        return 1
                    case "Dead":
                        return 0
                }
            })


export const getCellFrom: (matrix: Matrix, rowPosition: number, columnPosition: number) => Maybe<CellStatus> =
    (matrix: Matrix, rowPosition: number, columnPosition: number) =>
        isAValidCellPosition(matrix, rowPosition, columnPosition)
            ? maybe(matrix[rowPosition][columnPosition])
            : none()

const isAValidCellPosition: (matrix: CellStatus[][], rowPosition: number, columnPosition: number) => boolean =
    (matrix: CellStatus[][], rowPosition: number, columnPosition: number) =>
        rowPosition >= 0 && matrix.length > rowPosition && columnPosition >= 0 && matrix[rowPosition].length > columnPosition;
