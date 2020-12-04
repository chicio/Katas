import "regenerator-runtime/runtime";
import {calculateNextGeneration, getCellFrom} from "../src/GamePlay";
import {alive, dead} from "../src/Data";
import "../node_modules/fat-arrow-ts/jest-matchers";
import {none} from "fat-arrow-ts";

describe('GamePlay', () => {
    describe('calculateNeGeneration', () => {
        it('a single cell matrix should die', async () => {
            const matrix = await calculateNextGeneration([[alive()]]);

            expect(matrix).toEqual([[dead()]])
        });

        it('a 3x3 matrix with a single alive cell in the middle', async () => {
            const matrix = await calculateNextGeneration([
                [dead(), dead(), dead()],
                [dead(), alive(), dead()],
                [dead(), dead(), dead()]
            ]);

            expect(matrix).toEqual([
                [dead(), dead(), dead()],
                [dead(), dead(), dead()],
                [dead(), dead(), dead()]
            ])
        });

        it('a 3x3 matrix where cells has 2 neighbours and it is alive or have 3 neighbours and they are dead', async () => {
            const matrix = await calculateNextGeneration([
                [alive(), alive(), dead()],
                [dead(), alive(), dead()],
                [dead(), dead(), dead()]
            ]);

            expect(matrix).toEqual([
                [alive(), alive(), dead()],
                [alive(), alive(), dead()],
                [dead(), dead(), dead()]
            ])
        });

        it('a 4x4 matrix where cells has 2 neighbours and it is alive or have 3 neighbours and they are dead', async () => {
            const matrix = await calculateNextGeneration([
                [alive(), alive(), dead(), alive()],
                [dead(), alive(), alive(), dead()],
                [dead(), dead(), dead(), alive()],
                [dead(), dead(), alive(), alive()]
            ]);

            expect(matrix).toEqual([
                [alive(), alive(), dead(), dead()],
                [alive(), alive(), dead(), alive()],
                [dead(), alive(), dead(), alive()],
                [dead(), dead(), alive(), alive()]
            ])
        });
    });

    describe('getCellFrom', () => {
        it('a valid position', () => {
            const cellStatus = getCellFrom([[alive()]], 0, 0);

            expect(cellStatus).toBeRight(alive());
        });

        it('a invalid position', () => {
            const cellStatus = getCellFrom([[alive()]], 10, 0);

            expect(cellStatus).toBeLeft(none());
        });
    });
});
