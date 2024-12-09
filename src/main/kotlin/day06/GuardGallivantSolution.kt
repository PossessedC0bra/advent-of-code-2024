package day06

import AdventOfCodeSolution
import day06.GuardGallivantSolution.Direction.entries

object GuardGallivantSolution : AdventOfCodeSolution<Int> {

    override val day: Int = 6
    override val problemName: String = "Guard Gallivant"

    override fun part1(input: String): Int = parseInput(input)
        .let { (startPos, map) ->
            walk(map, startPos)
                .positions
                .count()
        }

    override fun part2(input: String): Int = parseInput(input)
        .let { (startPos, lab) ->
            walk(lab, startPos)
                .positions
                .map { walk(lab.copy(obstacles = (lab.obstacles + it)), startPos) }
                .count { it.isLoop }
        }

    data class Lab(val width: Int, val height: Int, val obstacles: Set<Position>)

    @JvmInline
    value class Position(private val pair: Pair<Int, Int>) {

        val x
            get() = pair.first
        val y
            get() = pair.second

        constructor(x: Int, y: Int) : this(x to y)

        operator fun plus(other: Position) = Position(x + other.x, y + other.y)
    }

    enum class Direction(val delta: Position) {
        UP(Position(0, -1)),
        RIGHT(Position(1, 0)),
        DOWN(Position(0, 1)),
        LEFT(Position(-1, 0));

        fun turnRight(): Direction = entries[(ordinal + 1) % entries.size]
    }

    data class Path(val positions: Set<Position>, val isLoop: Boolean = false)

    fun parseInput(input: String): Pair<Position, Lab> {
        var start: Position? = null
        val obstacles = mutableSetOf<Position>()

        val lines = input.lines()
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                when (char) {
                    '^' -> start = Position(x, y)
                    '#' -> obstacles.add(Position(x, y))
                }
            }
        }
        return start!! to Lab(lines.count(), lines.first().count(), obstacles)
    }

    fun walk(map: Lab, startPos: Position): Path {
        var pos = startPos
        var dir = Direction.UP

        val visited = mutableMapOf(pos to mutableSetOf(dir))
        while (true) {
            val next = pos + dir.delta
            when {
                next.x !in 0 until map.width || next.y !in 0 until map.height -> return Path(visited.keys)
                next in map.obstacles -> dir = dir.turnRight()
                else -> {
                    val directions = visited.getOrPut(next) { mutableSetOf() }
                    if (!directions.add(dir)) {
                        return Path(visited.keys, true)
                    }

                    pos = next
                }
            }
        }
    }
}