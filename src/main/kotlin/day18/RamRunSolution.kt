package day18

import AdventOfCodeSolution
import utils.IntVec2d
import java.util.*

typealias Position = IntVec2d

object RamRunSolution : AdventOfCodeSolution<Int> {

    override val day: Int = 18
    override val problemName: String = "RAM Run"

    override fun part1(input: String): Int = part1(input, 70, 70, 1024)

    fun part1(input: String, width: Int, height: Int, bytesToSimulate: Int): Int = parseInput(input)
        .take(bytesToSimulate)
        .let { corruptedPositions ->
            val start = Position(0, 0)
            val end = Position(width, height)
            val board = Board(width + 1, height + 1, corruptedPositions)

            findShortestPath(board, start, end)!!.distance
        }

    override fun part2(input: String): String = part2(input, 70, 70)

    fun part2(input: String, width: Int, height: Int): String = parseInput(input)
        .let { corruptedPositions ->
            val start = Position(0, 0)
            val end = Position(width, height)

            var firstImpossibleBitIndex = Integer.MAX_VALUE
            corruptedPositions.indices
                .toList()
                .binarySearch { idx ->
                    val board = Board(width + 1, height + 1, corruptedPositions.take(idx + 1))
                    if (findShortestPath(board, start, end) != null) -1 else (1).also {
                        firstImpossibleBitIndex = idx
                    }
                }

            return with(corruptedPositions[firstImpossibleBitIndex]) { "${x},${y}" }
        }

    fun parseInput(input: String): List<Position> = input
        .lines()
        .map { it.split(",") }
        .map { (x, y) -> Position(x.toInt(), y.toInt()) }

    fun findShortestPath(board: Board, source: Position, target: Position): Path? {
        var shortestPath: Path? = null

        val cache = mutableMapOf<Position, Int>()
        val queue = PriorityQueue<Path> { a, b -> a.cost(target).compareTo(b.cost(target)) }

        queue += Path(0, Direction.DOWN, listOf(source))
        queue += Path(0, Direction.RIGHT, listOf(source))

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            when {
                current.positions.last() !in board -> continue
                current.positions.last() == target -> {
                    shortestPath = current
                    break
                }
            }

            val key = current.positions.last()
            val previousDistance = cache[key] ?: Int.MAX_VALUE
            if (previousDistance <= current.distance) {
                continue
            }

            cache[key] = current.distance
            Direction.entries
                .filter { it != current.direction.opposite }
                .forEach { direction ->
                    queue += Path(
                        current.distance + 1,
                        direction,
                        current.positions + (current.positions.last() + direction.delta)
                    )
                }
        }

        return shortestPath
    }

    data class Board(val width: Int, val height: Int, val corruptedPositions: List<Position>) {

        operator fun contains(pos: Position): Boolean =
            pos.x in 0 until width && pos.y in 0 until height && pos !in corruptedPositions
    }

    data class Path(val distance: Int, val direction: Direction, val positions: List<Position>) {

        fun cost(target: Position): Int = distance + positions.last().manhattenDistance(target)
    }

    enum class Direction(val delta: Position) {
        UP(Position(0, -1)),
        RIGHT(Position(1, 0)),
        DOWN(Position(0, 1)),
        LEFT(Position(-1, 0));

        val opposite: Direction
            get() = when (this) {
                UP -> DOWN
                RIGHT -> LEFT
                DOWN -> UP
                LEFT -> RIGHT
            }
    }
}