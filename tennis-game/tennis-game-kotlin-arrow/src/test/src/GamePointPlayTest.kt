import GameInteraction.displayableGameScore
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class GamePointPlayTest {

    lateinit var game: Game

    @Before
    fun setUp() {
        game = Game()
    }

    @Test
    fun `player 1 score fifteen`() {
        val updatedGame = trackScoredPoint(Player1, game)

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Fifteen - Player 2 Love")))
    }

    @Test
    fun `player 1 score Thirty`() {
        val gameAfterFirstPlay = trackScoredPoint(Player1, game)
        val gameAfterSecondPlay = trackScoredPoint(Player1, gameAfterFirstPlay)

        val gameScore = displayableGameScore(gameAfterSecondPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Thirty - Player 2 Love")))
    }

    @Test
    fun `player 1 score Forty`() {
        val updatedGame =
            trackScoredPoint(Player1, game)
                .let { g -> trackScoredPoint(Player1, g) }
                .let { g -> trackScoredPoint(Player1, g) }

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Forty - Player 2 Love")))
    }

    @Test
    fun `player 2 score Fifteen`() {
        val gameAfterFirstPlay = trackScoredPoint(Player2, game)

        val gameScore = displayableGameScore(gameAfterFirstPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Love - Player 2 Fifteen")))
    }

    @Test
    fun `player 2 score Thirty`() {
        val updatedGame =
            trackScoredPoint(Player2, game)
                .let { g -> trackScoredPoint(Player2, g) }

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Love - Player 2 Thirty")))
    }

    @Test
    fun `player 2 score Forty`() {
        val updatedGame =
            trackScoredPoint(Player2, game)
                .let { g -> trackScoredPoint(Player2, g) }
                .let { g -> trackScoredPoint(Player2, g) }

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Love - Player 2 Forty")))
    }

    @Test
    fun Deuce() {
        val updatedGame =
            trackScoredPoint(Player1, game)
                .let { g -> trackScoredPoint(Player1, g)}
                .let { g -> trackScoredPoint(Player2, g)}
                .let { g -> trackScoredPoint(Player2, g)}
                .let { g -> trackScoredPoint(Player1, g)}
                .let { g -> trackScoredPoint(Player2, g)}

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Deuce")))
    }

    @Test
    fun `player 1 wins`() {
        val updatedGame =
            trackScoredPoint(Player1, game)
                .let { g -> trackScoredPoint(Player1, g)}
                .let { g -> trackScoredPoint(Player2, g)}
                .let { g -> trackScoredPoint(Player2, g)}
                .let { g -> trackScoredPoint(Player1, g)}
                .let { g -> trackScoredPoint(Player1, g)}

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 wins")))
    }

    @Test
    fun `player 2 wins`() {
        val updatedGame =
            trackScoredPoint(Player2, game)
                .let { g -> trackScoredPoint(Player2, g)}
                .let { g -> trackScoredPoint(Player1, g)}
                .let { g -> trackScoredPoint(Player1, g)}
                .let { g -> trackScoredPoint(Player2, g)}
                .let { g -> trackScoredPoint(Player2, g)}

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 2 wins")))
    }

    @Test
    fun `player 1 advantage`() {
        val updatedGame =
            trackScoredPoint(Player1, game)
                .let { g -> trackScoredPoint(Player2, g)}
                .let { g -> trackScoredPoint(Player1, g)}
                .let { g -> trackScoredPoint(Player2, g)}
                .let { g -> trackScoredPoint(Player1, g)}
                .let { g -> trackScoredPoint(Player2, g)}
                .let { g -> trackScoredPoint(Player1, g)}

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Advantage - Player 2 Forty")))
    }

    @Test
    fun `player 2 advantage`() {
        val updatedGame =
            trackScoredPoint(Player1, game)
                .let { g -> trackScoredPoint(Player2, g)}
                .let { g -> trackScoredPoint(Player1, g)}
                .let { g -> trackScoredPoint(Player2, g)}
                .let { g -> trackScoredPoint(Player1, g)}
                .let { g -> trackScoredPoint(Player2, g)}
                .let { g -> trackScoredPoint(Player2, g)}

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Forty - Player 2 Advantage")))
    }
}