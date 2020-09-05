import GameInteraction.displayableGameScore
import GamePlay.trackScorePointForPlayer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class GamePointPlayTest {

    private lateinit var game: Game

    @Before
    fun setUp() {
        game = Game()
    }

    @Test
    fun `player 1 score fifteen`() {
        val updatedGame = trackScorePointForPlayer(Player1, game)
        val gameScore = displayableGameScore(updatedGame)
        assertThat(gameScore, `is`(equalTo("Player 1 Fifteen - Player 2 Love")))
    }

    @Test
    fun `player 1 score Thirty`() {
        val gameAfterFirstPlay = trackScorePointForPlayer(Player1, game)
        val gameAfterSecondPlay = trackScorePointForPlayer(Player1, gameAfterFirstPlay)

        val gameScore = displayableGameScore(gameAfterSecondPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Thirty - Player 2 Love")))
    }

    @Test
    fun `player 1 score Forty`() {
        val updatedGame =
                trackScorePointForPlayer(Player1, game)
                        .let { g -> trackScorePointForPlayer(Player1, g) }
                        .let { g -> trackScorePointForPlayer(Player1, g) }

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Forty - Player 2 Love")))
    }

    @Test
    fun `player 2 score Fifteen`() {
        val gameAfterFirstPlay = trackScorePointForPlayer(Player2, game)

        val gameScore = displayableGameScore(gameAfterFirstPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Love - Player 2 Fifteen")))
    }

    @Test
    fun `player 2 score Thirty`() {
        val updatedGame =
                trackScorePointForPlayer(Player2, game)
                        .let { g -> trackScorePointForPlayer(Player2, g) }

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Love - Player 2 Thirty")))
    }

    @Test
    fun `player 2 score Forty`() {
        val updatedGame =
                trackScorePointForPlayer(Player2, game)
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Love - Player 2 Forty")))
    }

    @Test
    fun Deuce() {
        val updatedGame =
                trackScorePointForPlayer(Player1, game)
                        .let { g -> trackScorePointForPlayer(Player1, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player1, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Deuce")))
    }

    @Test
    fun `player 1 wins`() {
        val updatedGame =
                trackScorePointForPlayer(Player1, game)
                        .let { g -> trackScorePointForPlayer(Player1, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player1, g) }
                        .let { g -> trackScorePointForPlayer(Player1, g) }

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 wins")))
    }

    @Test
    fun `player 2 wins`() {
        val updatedGame =
                trackScorePointForPlayer(Player2, game)
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player1, g) }
                        .let { g -> trackScorePointForPlayer(Player1, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 2 wins")))
    }

    @Test
    fun `player 1 advantage`() {
        val updatedGame =
                trackScorePointForPlayer(Player1, game)
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player1, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player1, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player1, g) }

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Advantage - Player 2 Forty")))
    }

    @Test
    fun `player 2 advantage`() {
        val updatedGame =
                trackScorePointForPlayer(Player1, game)
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player1, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player1, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }
                        .let { g -> trackScorePointForPlayer(Player2, g) }

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Forty - Player 2 Advantage")))
    }
}