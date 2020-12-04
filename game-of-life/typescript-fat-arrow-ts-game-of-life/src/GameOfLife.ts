import {Matrix} from "./Data";
import {print, readInitialMatrix, welcome} from "./GameInteraction";
import {calculateNextGeneration} from "./GamePlay";

export const gameOfLife: Promise<Matrix> =
    welcome
        .then(() => readInitialMatrix
            .then((matrix) => print(matrix)
                .then((matrix) => gameLoop(matrix))
            )
        )

const gameLoop: (matrix: Matrix) => Promise<Matrix> = (matrix: Matrix) =>
    calculateNextGeneration(matrix)
        .then((matrix) => print(matrix)
            .then(matrix => gameLoop(matrix))
        )
