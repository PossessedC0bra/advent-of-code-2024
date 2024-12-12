package day12

import AdventOfCodeSolution
import java.util.*

object GardenGroups : AdventOfCodeSolution<Long> {

    override val day: Int = 12
    override val problemName: String = "Garden Groups"

    private val DIRECTIONS = listOf(
        Position(0, -1),  // Up
        Position(1, 0),   // Right
        Position(0, 1),   // Down
        Position(-1, 0)   // Left
    )

    override fun part1(input: String): Long {
        val regions = mutableListOf<MutableGarden>()
        val pointsAssignedToRegion = mutableSetOf<Position>()

        val lines = input.lines()
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, plant ->
                val startPos = Position(x, y)
                if (startPos in pointsAssignedToRegion) {
                    // point has already been assigned to a region -> do not try to create a region
                    return@forEachIndexed
                }

                val gardenRegion = MutableGarden(0, 0)

                val queue = ArrayDeque<Position>()
                queue += startPos
                while (queue.isNotEmpty()) {
                    val pos = queue.pop()
                    if (
                        (pos.x !in 0 until line.count() || pos.y !in 0 until lines.count())
                        || (lines[pos.y][pos.x] != plant)
                    ) {
                        // position is out of bounds or part of another region -> increase perimeter
                        gardenRegion.perimeter++
                        continue
                    }

                    if (pointsAssignedToRegion.add(pos)) {
                        // point has NOT already been assigned to a region -> merge with current region
                        gardenRegion.plots++
                        queue += DIRECTIONS.map { pos + it }
                    }
                }

                regions += gardenRegion
            }
        }

        return regions
            .sumOf { it.plots * it.perimeter }
            .toLong()
    }

    override fun part2(input: String): Long {
        TODO("Not yet implemented")
    }

    data class MutableGarden(var plots: Int, var perimeter: Int)

    data class Position(val x: Int, val y: Int) {

        operator fun plus(other: Position) = Position(x + other.x, y + other.y)
    }
}