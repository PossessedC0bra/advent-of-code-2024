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
        for ((y, line) in lines.withIndex()) {
            for ((x, plant) in line.withIndex()) {
                val startPos = Position(x, y)
                if (startPos in pointsAssignedToRegion) {
                    // point has already been visited (assigned to a region)
                    continue
                }

                val gardenPlot = MutableGarden(0, 0)
                val queue: LinkedList<Position> = LinkedList()
                queue += startPos
                while (queue.isNotEmpty()) {
                    val pos = queue.pop()
                    if (
                        (pos.x !in 0 until line.count() || pos.y !in 0 until lines.count())
                        || (lines[pos.y][pos.x] != plant)
                    ) {
                        // position is out of board or another region -> increase perimeter
                        gardenPlot.perimeter++
                        continue
                    }

                    if (pointsAssignedToRegion.add(pos)) {
                        // point has not already been assigned to a region
                        gardenPlot.plots++
                        queue += DIRECTIONS.map { pos + it }
                    }
                }

                regions += gardenPlot
            }
        }

        return regions.sumOf { it.plots * it.perimeter }.toLong()
    }

    override fun part2(input: String): Long {
        TODO("Not yet implemented")
    }

    data class MutableGarden(var plots: Int, var perimeter: Int)

    data class Position(val x: Int, val y: Int) {

        operator fun plus(other: Position) = Position(x + other.x, y + other.y)
    }
}