import {Task} from "fp-ts/Task";
import {createInterface} from "readline";

export const ask: (question: string) =>  Task<string> = (question: string) => () =>
    new Promise(resolve => {
        const rl = createInterface({
            input: process.stdin,
            output: process.stdout
        })
        rl.question(question, answer => {
            rl.close()
            resolve(answer)
        })
    })

export const puts: (message: string) => Task<void> = (message: string): Task<void> => () =>
    new Promise(res => {
        res(console.log(message))
    })
