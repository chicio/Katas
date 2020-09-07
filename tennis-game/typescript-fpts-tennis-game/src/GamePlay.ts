import {
    advantage,
    fifteen,
    forty,
    Game,
    gameToPlayer1Score,
    gameToPlayer2Score,
    Score,
    ScoringPlayer,
    thirty,
    wins
} from "./Data";
import {Lens} from "monocle-ts";
import {of, Task} from "fp-ts/Task";

export const trackScoredPoint: (scoringPlayer: ScoringPlayer, game: Game) => Task<Game> =
    (scoringPlayer: ScoringPlayer, game: Game) => of(trackScorePointForPlayer(scoringPlayer, game))

export const gameCompleted: (game: Game) => boolean = (game: Game) =>
    gameToPlayer1Score.get(game)._tag === 'Wins' || gameToPlayer2Score.get(game)._tag === 'Wins'

const trackScorePointForPlayer: (scoringPlayer: ScoringPlayer, game: Game) => Game =
    (scoringPlayer: ScoringPlayer, game: Game) => {
        switch (scoringPlayer._tag) {
            case 'Player1':
                return update(game, gameToPlayer1Score, gameToPlayer2Score)
            case 'Player2':
                return update(game, gameToPlayer2Score, gameToPlayer1Score)
        }
    }

const update: (game: Game, scoringPlayerScore: Lens<Game, Score>, opponentPlayerScore: Lens<Game, Score>) => Game =
    (game: Game, scoringPlayerScore: Lens<Game, Score>, opponentPlayerScore: Lens<Game, Score>) => {
        switch (scoringPlayerScore.get(game)._tag) {
            case 'Love':
                return scoringPlayerScore.set(fifteen())(game)
            case 'Fifteen':
                return scoringPlayerScore.set(thirty())(game)
            case 'Thirty':
                return scoringPlayerScore.set(forty())(game)
            case 'Forty': {
                switch (opponentPlayerScore.get(game)._tag) {
                    case 'Advantage':
                        return opponentPlayerScore.set(forty())(game)
                    case 'Forty':
                        return scoringPlayerScore.set(advantage())(game)
                    default:
                        return scoringPlayerScore.set(wins())(game)
                }
            }
            case 'Advantage':
                return scoringPlayerScore.set(wins())(game)
            case 'Wins':
                return game
        }
    }
