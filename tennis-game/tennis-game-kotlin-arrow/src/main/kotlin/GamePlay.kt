import arrow.optics.PLens

private val nextScore: (scoringPlayerCurrentScore: Score, opponentPlayerCurrentScore: Score) -> Pair<Score, Score> = { scoringPlayerCurrentScore, opponentPlayerCurrentScore  ->
    when (scoringPlayerCurrentScore) {
        Love -> Pair(Fifteen, opponentPlayerCurrentScore)
        Fifteen -> Pair(Thirty, opponentPlayerCurrentScore)
        Thirty -> Pair(Forty, opponentPlayerCurrentScore)
        Forty -> when(opponentPlayerCurrentScore) {
            Advantage -> Pair(Forty, Forty)
            Forty -> Pair(Advantage, opponentPlayerCurrentScore)
            else -> Pair(Wins, opponentPlayerCurrentScore)
        }
        Advantage -> Pair(Wins, opponentPlayerCurrentScore)
        Wins -> Pair(Wins, opponentPlayerCurrentScore)
    }
}

private val play: (game: Game,
                   scoringPlayerScore: PLens<Game, Game, Score, Score>,
                   opponentPlayerScore: PLens<Game, Game, Score, Score>) -> Game =
        { game, scoringPlayerScore, opponentPlayerScore ->
            val nextScoreScoringPlayer = nextScore(scoringPlayerScore.get(game), opponentPlayerScore.get(game))
            opponentPlayerScore.set(scoringPlayerScore.set(game, nextScoreScoringPlayer.first), nextScoreScoringPlayer.second)
        }

val player1Plays: (Game) -> Game = { game -> play(game, gamePlayer1Score, gamePlayer2Score) }

val player2Plays: (Game) -> Game = { game -> play(game, gamePlayer2Score, gamePlayer1Score) }

val displayableGameScore: (Game) -> String = { game ->
    val gamePlayer1Score = gamePlayer1Score.get(game)
    val gamePlayer2Score = gamePlayer2Score.get(game)

    when {
        gamePlayer1Score == Forty && gamePlayer2Score == Forty -> "Deuce"
        gamePlayer1Score == Wins -> "Player 1 wins"
        gamePlayer2Score == Wins -> "Player 2 wins"
        else -> "Player 1 ${showScore.run { gamePlayer1Score.show() }} - Player 2 ${showScore.run { gamePlayer2Score.show() }}"
    }
}

val gameWin: (Game) -> Boolean = { game ->
    gamePlayer1Score.get(game) == Wins || gamePlayer2Score.get(game) == Wins
}