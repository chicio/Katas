import {Lens} from "monocle-ts";
import {Show} from "fp-ts/Show";

export interface Love { readonly _tag: 'Love' }
export const love: () => Love = () => ({ _tag: 'Love' })
export interface Fifteen { readonly _tag: 'Fifteen' }
export const fifteen: () => Fifteen = () => ({ _tag: 'Fifteen' })
export interface Thirty { readonly _tag: 'Thirty' }
export const thirty: () => Thirty = () => ({ _tag: 'Thirty' })
export interface Forty { readonly _tag: 'Forty' }
export const forty: () => Forty = () => ({ _tag: 'Forty' })
export interface Advantage { readonly _tag: 'Advantage'}
export const advantage: () => Advantage = () => ({ _tag: 'Advantage' })
export interface Wins { readonly _tag: 'Wins' }
export const wins: () => Wins = () => ({ _tag: 'Wins' })
export type Score = Love | Fifteen | Thirty | Forty | Advantage | Wins

export const showScore: Show<Score> = {
    show(score: Score): string {
        return score._tag;
    }
}

export interface Player1 { readonly _tag: 'Player1' }
export const player1: () => Player1 = () => ({ _tag: 'Player1' })
export interface Player2 { readonly _tag: 'Player2' }
export const player2: () => Player2 = () => ({ _tag: 'Player2' })
export type ScoringPlayer = Player1 | Player2

export interface Player {
    readonly score: Score
}
export const player: (score: Score) => Player = (score: Score) => ({ score })

export interface Game {
    readonly player1: Player
    readonly player2: Player
}
export const game: (player1: Player, player2: Player) => Game = (player1: Player, player2: Player) => ({ player1, player2 })

export const createGame: () => Game = () => game(player(love()), player(love()));

const gamePlayer1: Lens<Game, Player> = Lens.fromProp<Game>()('player1')
const gamePlayer2: Lens<Game, Player> = Lens.fromProp<Game>()('player2')
const gameToPlayerScore: Lens<Player, Score> = Lens.fromProp<Player>()('score')
export const gameToPlayer1Score: Lens<Game, Score> = gamePlayer1.compose(gameToPlayerScore)
export const gameToPlayer2Score: Lens<Game, Score> = gamePlayer2.compose(gameToPlayerScore)
