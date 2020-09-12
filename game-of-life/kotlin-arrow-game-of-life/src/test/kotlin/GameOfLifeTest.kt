import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class GameOfLifeTest {

    @Test
    fun cellLessThanTwoNeighboursDies() {
        val grid = gameOfLife(Grid(arrayOf(
                arrayOf(Dead, Dead, Dead),
                arrayOf(Dead, Alive, Dead),
                arrayOf(Dead, Dead, Dead)
        )))

        assertThat(grid, `is`(equalTo(Grid(arrayOf(
                arrayOf(Dead, Dead, Dead),
                arrayOf(Dead, Dead, Dead),
                arrayOf(Dead, Dead, Dead)
        )))))
    }
}