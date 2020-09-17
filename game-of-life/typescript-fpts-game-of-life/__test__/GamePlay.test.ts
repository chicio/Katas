import {calculateNextGeneration} from "../src/GamePlay";
import {alive, dead} from "../src/Data";

describe('GamePlay', () => {
    it('a single cell matrix should die', async () => {
        const matrix = await calculateNextGeneration([[alive()]])();

        expect(matrix).toEqual([[dead()]])
    });

    it('a 3x3 matrix with a single alive cell in the middle', async () => {
        const matrix = await calculateNextGeneration([
            [dead(), dead(), dead()],
            [dead(), alive(), dead()],
            [dead(), dead(), dead()]
        ])();

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
        ])();

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
        ])();

        expect(matrix).toEqual([
            [alive(), alive(), dead(), dead()],
            [alive(), alive(), dead(), alive()],
            [dead(), alive(), dead(), alive()],
            [dead(), dead(), alive(), alive()]
        ])
    });
});
