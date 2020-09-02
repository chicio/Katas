import arrow.optics.Lens
import arrow.optics.PLens

data class Game(
        val player1: Player,
        val player2: Player
)

val player1: Lens<Game, Player> = Lens(
        get = { match -> match.player1 },
        set = { match, player -> match.copy(player1 = player) }
)

val player2: Lens<Game, Player> = Lens(
        get = { match -> match.player2 },
        set = { match, player -> match.copy(player2 = player) }
)

val gamePlayer1Score: PLens<Game, Game, Score, Score> = player1.compose(playerScore)

val gamePlayer2Score: PLens<Game, Game, Score, Score> = player2.compose(playerScore)
