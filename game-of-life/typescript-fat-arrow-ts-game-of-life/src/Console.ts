export const puts: (message: string) => Promise<void> =
    (message: string): Promise<void> => Promise.resolve(console.log(message))
