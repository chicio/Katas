
interface Love { readonly _tag: 'Love' }
interface Fifteen { readonly _tag: 'Fifteen' }
interface Thirty { readonly _tag: 'Thirty' }
interface Forty { readonly _tag: 'Forty' }
interface Advantage { readonly _tag: 'Advantage'}
interface Wins { readonly _tag: 'Wins' }
type Score = Love | Fifteen | Thirty | Forty | Advantage | Wins

interface Player1 { readonly _tag: 'PLayer1' }
interface Player2 { readonly _tag: 'PLayer2' }


interface Player {
    readonly score: Score
}

interface Game {
    readonly player1: Player
    readonly player2: Player
}
