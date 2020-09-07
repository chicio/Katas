import {Game, gameToPlayer1Score, gameToPlayer2Score, Score, showScore} from "./Data";

export const displayableGameScore: (game: Game) => string = (game: Game) => {
    const player1Score: Score = gameToPlayer1Score.get(game);
    const player2Score: Score = gameToPlayer2Score.get(game);

    if (player1Score._tag === 'Forty' && player2Score._tag === 'Forty') {
        return "Deuce"
    }

    if (player1Score._tag === 'Wins') {
        return "Player 1 wins"
    }

    if (player2Score._tag === 'Wins') {
        return "Player 2 wins"
    }

    return `Player 1 ${showScore.show(player1Score)} - Player 2 ${showScore.show(player2Score)}`
}
