package day16

import AdventOfCodeSolution
import day16.ReindeerMazeSolution.Direction.entries
import utils.CharBoard
import utils.IntVec2d
import java.util.*

typealias Position = IntVec2d

object ReindeerMazeSolution : AdventOfCodeSolution<Long> {

    override val day: Int = 16
    override val problemName: String = "Reindeer Maze"

    const val WALL = '#'
    const val START = 'S'
    const val END = 'E'

    override fun part1(input: String): Long {
        val board = CharBoard(input)
        val startPos = board.find { it == START }!!
        val endPos = board.find { it == END }!!

        return findShortestPath(board, startPos, endPos).distance
    }

    override fun part2(input: String): Long {
        val board = CharBoard(input)
        val startPos = board.find { it == START }!!
        val endPos = board.find { it == END }!!

        return findShortestPaths(board, startPos, endPos)
            .flatMap { it.positions }
            .toSet()
            .count()
            .toLong()
    }

    fun findShortestPath(board: CharBoard, startPos: Position, endPos: Position) =
        findShortestPaths(board, startPos, endPos).first()


    fun findShortestPaths(board: CharBoard, startPos: Position, endPos: Position): List<Path> {
        var minDistance = Long.MAX_VALUE
        var shortestPaths = mutableListOf<Path>()

        val bestDistanceCache = mutableMapOf<Pair<Position, Direction>, Long>()

        val queue = PriorityQueue<Reindeer>(compareBy(Reindeer::distance))
        queue.add(Reindeer(listOf(startPos), Direction.EAST, 0))

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            when {
                current.position !in board || board[current.position] == WALL -> continue
                current.position == endPos -> {
                    // all the best paths have been found
                    if (current.distance > minDistance) {
                        break
                    }

                    minDistance = current.distance
                    shortestPaths.add(Path(current.distance, current.path))
                    continue
                }
            }

            val positionWithDirection = current.position to current.direction
            // stop following this path if it's longer than a path we've already found
            if (current.distance > (bestDistanceCache[positionWithDirection] ?: Long.MAX_VALUE)) {
                continue
            }

            bestDistanceCache[positionWithDirection] = current.distance
            current.nextMoves().forEach { queue.add(it) }
        }

        return shortestPaths
    }

    data class Path(val distance: Long, val positions: List<Position>)

    data class Reindeer(
        val path: List<Position>,
        val direction: Direction,
        val distance: Long,
    ) {

        val position
            get() = path.last()

        fun nextMoves() = listOf(
            Reindeer(path + (path.last() + direction.delta), direction, distance + 1),
            Reindeer(
                path + (path.last() + direction.turnClockwise().delta),
                direction.turnClockwise(),
                distance + 1000 + 1
            ),
            Reindeer(
                path + (path.last() + direction.turnCounterclockwise().delta),
                direction.turnCounterclockwise(),
                distance + 1000 + 1
            )
        )
    }

    enum class Direction(val delta: IntVec2d) {
        NORTH(IntVec2d(0, -1)),
        EAST(IntVec2d(1, 0)),
        SOUTH(IntVec2d(0, 1)),
        WEST(IntVec2d(-1, 0));

        fun turnClockwise() = entries[(this.ordinal + 1) % entries.count()]
        fun turnCounterclockwise() = entries[(this.ordinal + entries.count() - 1) % entries.count()]

    }
}