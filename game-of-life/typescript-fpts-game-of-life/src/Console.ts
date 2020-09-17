import {Task} from "fp-ts/Task";

export const puts: (message: string) => Task<void> = (message: string): Task<void> => () =>
    new Promise(res => {
        res(console.log(message))
    })
