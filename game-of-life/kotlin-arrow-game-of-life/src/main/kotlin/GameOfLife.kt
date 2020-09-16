import Console.puts
import GamePlay.calculateNextGeneration
import arrow.fx.IO
import arrow.fx.IO.Companion.just
import arrow.fx.extensions.fx

object GameOfLife {
    fun gameOfLife(): IO<Matrix> = welcome()
            .flatMap { readInitialMatrix() }
            .flatMap { gameLoop(it) }

    private fun welcome(): IO<Unit> = puts("Welcome to the Conway's Game of Life!")

    private fun readInitialMatrix(): IO<Matrix> = just(
            listOf(
                    listOf(Dead, Alive, Dead),
                    listOf(Dead, Alive, Dead),
                    listOf(Dead, Alive, Dead)
            )
    )

    private fun print(matrix: Matrix): IO<Matrix> = puts(showMatrix.run { matrix.show() })
            .flatMap { just(matrix) }

    private fun gameLoop(matrix: Matrix): IO<Matrix> = IO.fx {
        val currentMatrix = !print(matrix)
        val nextMatrix = !calculateNextGeneration(currentMatrix)
        !gameLoop(nextMatrix)
    }
}
