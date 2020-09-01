import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class GameTest {

    @Test
    fun `player 1 score fifteen`() {
        val game = Game(Player(Love), Player(Love))
        val updatedGame = player1Plays(game)

        val gameScore = getGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Fifteen - Player 2 Love")))
    }

    @Test
    fun `player 1 score Thirty`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = player1Plays(game)
        val gameAfterSecondPlay = player1Plays(gameAfterFirstPlay)

        val gameScore = getGameScore(gameAfterSecondPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Thirty - Player 2 Love")))
    }

    @Test
    fun `player 1 score Forty`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = player1Plays(game)
        val gameAfterSecondPlay = player1Plays(gameAfterFirstPlay)
        val gameAfterThirdPlay = player1Plays(gameAfterSecondPlay)

        val gameScore = getGameScore(gameAfterThirdPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Forty - Player 2 Love")))
    }

    @Test
    fun `player 2 score Fifteen`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = player2Plays(game)

        val gameScore = getGameScore(gameAfterFirstPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Love - Player 2 Fifteen")))
    }

    @Test
    fun `player 2 score Thirty`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = player2Plays(game)
        val gameAfterSecondPlay = player2Plays(gameAfterFirstPlay)

        val gameScore = getGameScore(gameAfterSecondPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Love - Player 2 Thirty")))
    }

    @Test
    fun `player 2 score Forty`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = player2Plays(game)
        val gameAfterSecondPlay = player2Plays(gameAfterFirstPlay)
        val gameAfterThirdPlay = player2Plays(gameAfterSecondPlay)

        val gameScore = getGameScore(gameAfterThirdPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Love - Player 2 Forty")))
    }

    @Test
    fun `Deuce`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = player1Plays(game)
        val gameAfterSecondPlay = player2Plays(gameAfterFirstPlay)
        val gameAfterThirdPlay = player1Plays(gameAfterSecondPlay)
        val gameAfterForthPlay = player2Plays(gameAfterThirdPlay)
        val gameAfterFifthPlay = player1Plays(gameAfterForthPlay)
        val gameAfterSixthPlay = player2Plays(gameAfterFifthPlay)

        val gameScore = getGameScore(gameAfterSixthPlay)

        assertThat(gameScore, `is`(equalTo("Deuce")))
    }
}