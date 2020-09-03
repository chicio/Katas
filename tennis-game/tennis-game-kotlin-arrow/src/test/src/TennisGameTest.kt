import org.junit.Assert
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream

class TennisGameTest {

    @Test
    fun aCompleteGame() {
        val result = execute(inputs("1", "1", "1", "1"))

        val expected = outputs(
                "Welcome to the Tennis Game!",
                "Which player will play (1 or 2)?",
                "Player 1 Fifteen - Player 2 Love",
                "Which player will play (1 or 2)?",
                "Player 1 Thirty - Player 2 Love",
                "Which player will play (1 or 2)?",
                "Player 1 Forty - Player 2 Love",
                "Which player will play (1 or 2)?",
                "Player 1 wins"
        )

        Assert.assertEquals(result, expected)
    }

    @Test
    fun invalidPlayer() {
        val result = execute(inputs("1", "1", "99", "1", "1"))

        val expected = outputs(
                "Welcome to the Tennis Game!",
                "Which player will play (1 or 2)?",
                "Player 1 Fifteen - Player 2 Love",
                "Which player will play (1 or 2)?",
                "Player 1 Thirty - Player 2 Love",
                "Which player will play (1 or 2)?",
                "Invalid player selected. Try again.",
                "Which player will play (1 or 2)?",
                "Player 1 Forty - Player 2 Love",
                "Which player will play (1 or 2)?",
                "Player 1 wins"
        )

        Assert.assertEquals(result, expected)
    }

    private fun inputs(vararg value: String): String =
            value.joinToString(enter)

    private fun outputs(vararg value: String): String =
            value.joinToString(enter) + enter

    private fun execute(inputs: String): String  {
        val swapStreams = { inputStream: InputStream, printStream: PrintStream ->
            System.setIn(inputStream)
            System.setOut(printStream)
        }
        val initialOut = System.out
        val initialIn = System.`in`
        val byteArrayOutputStream = ByteArrayOutputStream()
        swapStreams(ByteArrayInputStream(inputs.toByteArray()), PrintStream(byteArrayOutputStream))
        tennisGame().unsafeRunSync()
        swapStreams(initialIn, initialOut)
        return byteArrayOutputStream.toString()
    }

    private val enter = System.getProperty("line.separator")


}