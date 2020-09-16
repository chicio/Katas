import GamePlay.calculateNextGeneration
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class GamePlayTest {

    @Test
    fun `a single cell matrix should die`() {
        val nextGenerationMatrix = calculateNextGeneration(listOf(
                listOf(Alive)
        )).unsafeRunSync()

        assertThat(nextGenerationMatrix, `is`(equalTo(listOf(listOf(Dead)) as Matrix)))
    }

    @Test
    fun `a 3x3 matrix with a single alive cell in the middle`() {
        val nextGenerationMatrix = calculateNextGeneration(listOf(
                listOf(Dead, Dead, Dead),
                listOf(Dead, Alive, Dead),
                listOf(Dead, Dead, Dead)
        )).unsafeRunSync()

        assertThat(nextGenerationMatrix, `is`(equalTo(listOf(
                listOf(Dead, Dead, Dead),
                listOf(Dead, Dead, Dead),
                listOf(Dead, Dead, Dead)
        ) as Matrix)))
    }

    @Test
    fun `a 3x3 matrix where cells has 2 neighbours and it is alive or have 3 neighbours and they are dead`() {
        val nextGenerationMatrix = calculateNextGeneration(listOf(
                listOf(Alive, Alive, Dead),
                listOf(Dead, Alive, Dead),
                listOf(Dead, Dead, Dead)
        )).unsafeRunSync()

        assertThat(nextGenerationMatrix, `is`(equalTo(listOf(
                listOf(Alive, Alive, Dead),
                listOf(Alive, Alive, Dead),
                listOf(Dead, Dead, Dead)
        ) as Matrix)))
    }

    @Test
    fun `a 4x4 matrix where cells has 2 neighbours and it is alive or have 3 neighbours and they are dead`() {
        val nextGenerationMatrix = calculateNextGeneration(listOf(
                listOf(Alive, Alive, Dead, Alive),
                listOf(Dead, Alive, Alive, Dead),
                listOf(Dead, Dead, Dead, Alive),
                listOf(Dead, Dead, Alive, Alive)
        )).unsafeRunSync()

        assertThat(nextGenerationMatrix, `is`(equalTo(listOf(
                listOf(Alive, Alive, Dead, Dead),
                listOf(Alive, Alive, Dead, Alive),
                listOf(Dead, Alive, Dead, Alive),
                listOf(Dead, Dead, Alive, Alive)
        ) as Matrix)))
    }

}