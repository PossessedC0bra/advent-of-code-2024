package day14

import AdventOfCodeSolution
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object RestroomRedoubtSolution : AdventOfCodeSolution<Long> {

    override val day: Int = 14
    override val problemName: String = "Restroom Redoubt"

    private const val WIDTH = 101
    private const val HEIGHT = 103

    private val ROBOT_REGEX = """p=(-?\d+),(-?\d+) v=(-?\d+),(-?\d+)""".toRegex()

    override fun part1(input: String): Long = part1(input, WIDTH, HEIGHT, 100)

    fun part1(input: String, width: Int, height: Int, numSeconds: Int): Long = parseInput(input)
        .map { (pos, velocity) -> (pos + (velocity * numSeconds)).wrap(width.toLong(), height.toLong()) }
        .fold(longArrayOf(0, 0, 0, 0)) { acc, pos ->
            when {
                pos.x < width / 2 && pos.y < height / 2 -> acc[0]++
                pos.x > width / 2 && pos.y < height / 2 -> acc[1]++
                pos.x < width / 2 && pos.y > height / 2 -> acc[2]++
                pos.x > width / 2 && pos.y > height / 2 -> acc[3]++
            }

            acc
        }
        .reduce { a, b -> a * b }

    /**
     * Part 2 asks us to find a christmas tree in the board of robots. As i did not find a programmatic way to figure
     * out whether a christmas tree is drawn on the board, i decided to iterate over all possible positions and save
     * the board as a png image
     *
     * When inspecting the images, I found interesting patterns appearing at iteration 12 (repeating at an interval
     * equal to the width of the board) and 64 (repeating at an interval equal to the height of the board).
     *
     * That's when I decided to only print the board when the iteration has a remainder of 12 and 64 when divided by the
     * width and height of the board respectively.
     */
    override fun part2(input: String): Long {
        var robots = parseInput(input).toMutableList()
        repeat(10_000) { iteration ->
            robots = robots
                .map { (pos, velocity) -> ((pos + velocity).wrap(WIDTH.toLong(), HEIGHT.toLong())) to velocity }
                .toMutableList()

            // print the board when the iteration has a remainder of 12 and 64 when divided by the width and height
            // of the board respectively
            if (iteration % WIDTH == 12 && iteration % HEIGHT == 64) {
                val positions = robots.map { it.first }.toSet()

                BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_BINARY)
                    .apply {
                        (0 until HEIGHT).forEach { y ->
                            (0 until WIDTH).forEach { x ->
                                if (positions.contains(Position(x.toLong(), y.toLong()))) {
                                    setRGB(x, y, 0xFFFFFF)
                                }
                            }
                        }
                    }
                    .also {
                        ImageIO.write(
                            it,
                            "png",
                            File("./out/day14/${iteration + 1}_seconds.png").apply { parentFile.mkdirs() })
                    }

                return iteration + 1L
            }
        }

        throw IllegalStateException("No solution found... Try iterating more times :)")
    }

    private fun parseInput(input: String): List<Pair<Position, Position>> = ROBOT_REGEX
        .findAll(input)
        .map {
            val (startX, startY, velocityX, velocityY) = it.destructured
            Position(startX.toLong(), startY.toLong()) to Position(velocityX.toLong(), velocityY.toLong())
        }
        .toList()

    data class Position(val x: Long, val y: Long) {

        operator fun plus(other: Position) = Position(x + other.x, y + other.y)

        operator fun times(b: Int) = Position(x * b, y * b)

        /**
         * Wraps the position around the given width and height. Always produces a positive position.
         *
         * Mathematically speaking a modulo operation that always produces a positive result is applied to the
         * positions x and y values.
         */
        fun wrap(width: Long, height: Long): Position {
            val wrappedX = (x % width)
            val wrappedY = (y % height)

            return Position(
                if (wrappedX < 0) wrappedX + width else wrappedX,
                if (wrappedY < 0) wrappedY + height else wrappedY
            )
        }
    }
}