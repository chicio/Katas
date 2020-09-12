import arrow.optics.Optional

sealed class CellStatus
object Alive: CellStatus()
object Dead: CellStatus()

typealias Content = Array<Array<out CellStatus>>

data class Grid(
    val content: Content
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Grid
        if (!content.contentDeepEquals(other.content)) return false
        return true
    }

    override fun hashCode(): Int {
        return content.contentDeepHashCode()
    }
}



