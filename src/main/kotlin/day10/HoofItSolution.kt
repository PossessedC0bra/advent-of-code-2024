package day10

import AdventOfCodeSolution

object HoofItSolution : AdventOfCodeSolution<Long> {

    override val day: Int = 10
    override val problemName: String = "Hoof It"

    override fun part1(input: String): Long = parseInput(input)
        .let { (trailHeads, map) ->
            trailHeads
                .map { trailHead -> followTrails(trailHead, map) }
                .map { peaks -> peaks.toSet() }
                .sumOf { uniquePeaks -> uniquePeaks.count() }
                .toLong()
        }

    override fun part2(input: String): Long = parseInput(input)
        .let { (trailHeads, map) ->
            trailHeads
                .map { trailHead -> followTrails(trailHead, map) }
                .sumOf { peaks -> peaks.count() }
                .toLong()
        }

    fun parseInput(input: String): Pair<List<Position>, HikingMap> {
        val lines = input.lines()

        val trailHeads = mutableListOf<Position>()
        val positions = lines
            .flatMapIndexed { y, line ->
                line.mapIndexed { x, char ->
                    val number = char.digitToInt()
                    if (number == 0) {
                        trailHeads += Position(x, y)
                    }
                    number
                }
            }
            .toIntArray()

        return trailHeads to HikingMap(positions, lines.first().count(), lines.count())
    }

    fun followTrails(trailHead: Position, map: HikingMap) = followTrailsRecursive(map, trailHead, -1)

    fun followTrailsRecursive(map: HikingMap, pos: Position, previousHeight: Int): List<Position> {
        if (pos.x !in 0 until map.width || pos.y !in 0 until map.height) {
            return emptyList()
        }

        val height = map[pos.x, pos.y]
        when {
            height - previousHeight != 1 -> return emptyList()
            height == 9 -> return listOf(pos)
        }

        return map.getPeaksReached(pos.x, pos.y) ?: run {
            val peaks = DIRECTIONS.flatMap { followTrailsRecursive(map, pos + it, height) }
            map.setPeaksReached(pos.x, pos.y, peaks)
            peaks
        }
    }

    private val DIRECTIONS = listOf(
        Position(0, -1),  // Up
        Position(1, 0),   // Right
        Position(0, 1),   // Down
        Position(-1, 0)   // Left
    )

    @Suppress("ArrayInDataClass")
    data class HikingMap(val topographicMap: IntArray, val width: Int, val height: Int) {

        private val peaksReached: Array<List<Position>?> = Array(width * height) { _ -> null }

        operator fun get(x: Int, y: Int) = topographicMap[y * height + x]

        fun getPeaksReached(x: Int, y: Int): List<Position>? = peaksReached[y * height + x]
        fun setPeaksReached(x: Int, y: Int, peaks: List<Position>) {
            peaksReached[y * height + x] = peaks
        }
    }

    data class Position(val x: Int, val y: Int) {

        operator fun plus(other: Position) = Position(x + other.x, y + other.y)

        operator fun unaryMinus() = Position(-x, -y)
        operator fun minus(other: Position) = Position(x - other.x, y - other.y)

        operator fun times(multiple: Int) = Position(x * multiple, y * multiple)
    }
}