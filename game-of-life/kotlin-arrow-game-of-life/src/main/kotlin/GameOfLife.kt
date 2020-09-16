import GameInteraction.print
import GameInteraction.readInitialMatrix
import GameInteraction.welcome
import GamePlay.calculateNextGeneration
import arrow.fx.IO
import arrow.fx.extensions.fx

object GameOfLife {
    fun gameOfLife(): IO<Matrix> = welcome()
            .flatMap { readInitialMatrix() }
            .flatMap { gameLoop(it) }

    private fun gameLoop(matrix: Matrix): IO<Matrix> = IO.fx {
        val currentMatrix = !print(matrix)
        val nextMatrix = !calculateNextGeneration(currentMatrix)
        !gameLoop(nextMatrix)
    }
}
