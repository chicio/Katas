import GameInteraction.welcome
import arrow.fx.IO

val tennisGame: () -> IO<Game> = {
    welcome.flatMap { playTennisGame(Game(Player(Love), Player(Love))) }
}

fun main() {
    tennisGame().unsafeRunSync()
}