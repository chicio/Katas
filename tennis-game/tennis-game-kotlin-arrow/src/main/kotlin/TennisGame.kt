import GameInteraction.welcome
import GamePlay.playTennisGame
import arrow.fx.IO

val tennisGame: () -> IO<Game> = {
    welcome.flatMap { playTennisGame(Game()) }
}

fun main() {
    tennisGame().unsafeRunSync()
}