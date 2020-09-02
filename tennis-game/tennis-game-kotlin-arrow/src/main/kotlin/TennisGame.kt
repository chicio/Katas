import arrow.fx.IO
import arrow.fx.extensions.fx

fun playTennisGame(game: Game): IO<Game> =
    IO.fx {
        val updatedGame = askPlayerPlays(game).bind()
        printGameScore(updatedGame).bind()
        when {
            gameWin(updatedGame) -> updatedGame
            else -> playTennisGame(updatedGame).bind()
        }
    }

val tennisGame: () -> IO<Any> = {
    welcome().flatMap { playTennisGame(Game(Player(Love), Player(Love))) }
}
