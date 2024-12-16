package utils

class CharBoard(input: String) {
    private val charBuffer: CharArray
    val width: Int
    val height: Int

    init {
        val rows = input.lines()
        require(rows.isNotEmpty()) { "Input must contain at least one row." }
        val firstRowLength = rows.first().length
        require(rows.all { it.length == firstRowLength }) { "All rows must have the same length." }

        this.width = firstRowLength
        this.height = rows.count()
        this.charBuffer = rows.joinToString("").toCharArray()
    }

    fun find(predicate: (Char) -> Boolean): IntVec2d? {
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (predicate(get(x, y))) {
                    return IntVec2d(x, y)
                }
            }
        }
        return null
    }

    operator fun get(pos: IntVec2d): Char = get(pos.x, pos.y)

    operator fun get(x: Int, y: Int): Char {
        require(x in 0 until width) { "x coordinate out of bounds: $x" }
        require(y in 0 until height) { "y coordinate out of bounds: $y" }
        return charBuffer[y * height + x]
    }

    operator fun contains(char: Char) = find { it == char } != null

    operator fun contains(pos: IntVec2d): Boolean {
        return pos.x in 0 until width && pos.y in 0 until height
    }

    fun forEach(action: (Char) -> Unit) = charBuffer.forEach(action)

    fun forEachIndexed(action: (IntVec2d, Char) -> Unit) {
        for (y in 0 until height) {
            for (x in 0 until width) {
                action(IntVec2d(x, y), get(x, y))
            }
        }
    }

    fun <T> map(transform: (Char) -> T) = charBuffer.map(transform)

    override fun toString(): String {
        val builder = StringBuilder()
        for (y in 0 until height) {
            builder.append(charBuffer, y * width, width).append("\n")
        }
        return builder.toString()
    }
}