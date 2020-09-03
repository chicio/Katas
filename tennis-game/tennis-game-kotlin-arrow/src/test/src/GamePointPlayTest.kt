import GameInteraction.displayableGameScore
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class GamePointPlayTest {

    @Test
    fun `player 1 score fifteen`() {
        val game = Game(Player(Love), Player(Love))
        val updatedGame = trackScoredPoint(Player1, game)

        val gameScore = displayableGameScore(updatedGame)

        assertThat(gameScore, `is`(equalTo("Player 1 Fifteen - Player 2 Love")))
    }

    @Test
    fun `player 1 score Thirty`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = trackScoredPoint(Player1, game)
        val gameAfterSecondPlay = trackScoredPoint(Player1, gameAfterFirstPlay)

        val gameScore = displayableGameScore(gameAfterSecondPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Thirty - Player 2 Love")))
    }

    @Test
    fun `player 1 score Forty`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = trackScoredPoint(Player1, game)
        val gameAfterSecondPlay = trackScoredPoint(Player1, gameAfterFirstPlay)
        val gameAfterThirdPlay = trackScoredPoint(Player1, gameAfterSecondPlay)

        val gameScore = displayableGameScore(gameAfterThirdPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Forty - Player 2 Love")))
    }

    @Test
    fun `player 2 score Fifteen`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = trackScoredPoint(Player2, game)

        val gameScore = displayableGameScore(gameAfterFirstPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Love - Player 2 Fifteen")))
    }

    @Test
    fun `player 2 score Thirty`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = trackScoredPoint(Player2, game)
        val gameAfterSecondPlay = trackScoredPoint(Player2, gameAfterFirstPlay)

        val gameScore = displayableGameScore(gameAfterSecondPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Love - Player 2 Thirty")))
    }

    @Test
    fun `player 2 score Forty`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = trackScoredPoint(Player2, game)
        val gameAfterSecondPlay = trackScoredPoint(Player2, gameAfterFirstPlay)
        val gameAfterThirdPlay = trackScoredPoint(Player2, gameAfterSecondPlay)

        val gameScore = displayableGameScore(gameAfterThirdPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Love - Player 2 Forty")))
    }

    @Test
    fun `Deuce`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = trackScoredPoint(Player1, game)
        val gameAfterSecondPlay = trackScoredPoint(Player1, gameAfterFirstPlay)
        val gameAfterThirdPlay = trackScoredPoint(Player2, gameAfterSecondPlay)
        val gameAfterForthPlay = trackScoredPoint(Player2, gameAfterThirdPlay)
        val gameAfterFifthPlay = trackScoredPoint(Player1, gameAfterForthPlay)
        val gameAfterSixthPlay = trackScoredPoint(Player2, gameAfterFifthPlay)

        val gameScore = displayableGameScore(gameAfterSixthPlay)

        assertThat(gameScore, `is`(equalTo("Deuce")))
    }

    @Test
    fun `player 1 wins`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = trackScoredPoint(Player1, game)
        val gameAfterSecondPlay = trackScoredPoint(Player1, gameAfterFirstPlay)
        val gameAfterThirdPlay = trackScoredPoint(Player2, gameAfterSecondPlay)
        val gameAfterForthPlay = trackScoredPoint(Player2, gameAfterThirdPlay)
        val gameAfterFifthPlay = trackScoredPoint(Player1, gameAfterForthPlay)
        val gameAfterSixthPlay = trackScoredPoint(Player1, gameAfterFifthPlay)

        val gameScore = displayableGameScore(gameAfterSixthPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 wins")))
    }

    @Test
    fun `player 2 wins`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = trackScoredPoint(Player2, game)
        val gameAfterSecondPlay = trackScoredPoint(Player2, gameAfterFirstPlay)
        val gameAfterThirdPlay = trackScoredPoint(Player1, gameAfterSecondPlay)
        val gameAfterForthPlay = trackScoredPoint(Player1, gameAfterThirdPlay)
        val gameAfterFifthPlay = trackScoredPoint(Player2, gameAfterForthPlay)
        val gameAfterSixthPlay = trackScoredPoint(Player2, gameAfterFifthPlay)

        val gameScore = displayableGameScore(gameAfterSixthPlay)

        assertThat(gameScore, `is`(equalTo("Player 2 wins")))
    }

    @Test
    fun `player 1 advantage`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = trackScoredPoint(Player1, game)
        val gameAfterSecondPlay = trackScoredPoint(Player2, gameAfterFirstPlay)
        val gameAfterThirdPlay = trackScoredPoint(Player1, gameAfterSecondPlay)
        val gameAfterForthPlay = trackScoredPoint(Player2, gameAfterThirdPlay)
        val gameAfterFifthPlay = trackScoredPoint(Player1, gameAfterForthPlay)
        val gameAfterSixthPlay = trackScoredPoint(Player2, gameAfterFifthPlay)
        val gameAfterSeventhPlay = trackScoredPoint(Player1, gameAfterSixthPlay)

        val gameScore = displayableGameScore(gameAfterSeventhPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Advantage - Player 2 Forty")))
    }

    @Test
    fun `player 2 advantage`() {
        val game = Game(Player(Love), Player(Love))
        val gameAfterFirstPlay = trackScoredPoint(Player1, game)
        val gameAfterSecondPlay = trackScoredPoint(Player2, gameAfterFirstPlay)
        val gameAfterThirdPlay = trackScoredPoint(Player1, gameAfterSecondPlay)
        val gameAfterForthPlay = trackScoredPoint(Player2, gameAfterThirdPlay)
        val gameAfterFifthPlay = trackScoredPoint(Player1, gameAfterForthPlay)
        val gameAfterSixthPlay = trackScoredPoint(Player2, gameAfterFifthPlay)
        val gameAfterSeventhPlay = trackScoredPoint(Player2, gameAfterSixthPlay)

        val gameScore = displayableGameScore(gameAfterSeventhPlay)

        assertThat(gameScore, `is`(equalTo("Player 1 Forty - Player 2 Advantage")))
    }
}