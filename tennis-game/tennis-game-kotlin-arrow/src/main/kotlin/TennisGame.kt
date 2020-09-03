import arrow.fx.IO
import arrow.fx.extensions.fx

fun playTennisGame(game: Game): IO<Game> =
        IO.fx {
            (!askPlayerThatWillPlay()).fold(
                    {
                        !showPlayerSelectionError()
                        !playTennisGame(game)
                    },
                    { selectedPLayer ->
                        val updatedGame = playerPlays(selectedPLayer, game)
                        !showGameScore(updatedGame)
                        when {
                            gameWin(updatedGame) -> updatedGame
                            else -> !playTennisGame(updatedGame)
                        }
                    })
        }

val tennisGame: () -> IO<Game> = {
    welcome().flatMap { playTennisGame(Game(Player(Love), Player(Love))) }
}
