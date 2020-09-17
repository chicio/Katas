import {Show} from "fp-ts/Show";

export interface Alive { readonly _tag: 'Alive' }
export const alive: () => Alive = () => ({ _tag: 'Alive' })
export interface Dead { readonly _tag: 'Dead' }
export const dead: () => Dead = () => ({ _tag: 'Dead' })
export type CellStatus = Alive | Dead

export type Matrix = CellStatus[][]

export const cellStatusToSymbol: (cellStatus: CellStatus) => string = (cellStatus: CellStatus) => {
    switch (cellStatus._tag) {
        case 'Alive': return '*'
        case 'Dead': return 'X'
    }
}

export const showMatrix: Show<Matrix> = {
    show(matrix: Matrix): string {
        return matrix.reduce((matrixString, currentRow) => {
            return currentRow.reduce((rowString, currentCell) => {
                return `${rowString}${cellStatusToSymbol(currentCell)} `
            }, matrixString)
        }, '')
    }
}
