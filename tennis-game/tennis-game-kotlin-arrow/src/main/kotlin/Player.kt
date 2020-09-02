import arrow.optics.Lens
import arrow.optics.PLens

data class Player(
        val score: Score
)

val playerScore: Lens<Player, Score> = PLens(
        get = { player -> player.score },
        set = { player, score -> player.copy(score = score) }
)

sealed class PossiblePlayer
object Player1: PossiblePlayer()
object Player2: PossiblePlayer()