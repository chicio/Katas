import Console.puts
import arrow.fx.IO

object GameInteraction {
    fun welcome(): IO<Unit> = puts("Welcome to the Conway's Game of Life!")

    fun readInitialMatrix(): IO<Matrix> =
            IO.just(parse(
                    this::class.java.classLoader
                            .getResource("4x4-block.csv")!!
                            .readText())
            )

    private fun parse(input: String): Matrix = input
            .split("\n")
            .map { line ->
                line.split(",").map { element ->
                    when (element) {
                        "Dead" -> Dead
                        else -> Alive
                    }
                }
            }

    fun print(matrix: Matrix): IO<Matrix> = puts(showMatrix.run { matrix.show() })
            .flatMap { IO.just(matrix) }
}