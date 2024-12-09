package day08

import AdventOfCodeSolution

object ResonantCollinearitySolution : AdventOfCodeSolution<Long> {

    override val day: Int = 8
    override val problemName: String = "Resonant Collinearity"

    override fun part1(input: String): Long = parseInput(input)
        .let { antennaMap ->
            antennaMap
                .antennas
                .flatMap { (_, antennaPositions) ->
                    val antinodes = mutableListOf<Position>()

                    for ((idx, antennaPos) in antennaPositions.withIndex()) {
                        for (otherIdx in (idx + 1 until antennaPositions.count())) {
                            val otherAntennaPos = antennaPositions[otherIdx]
                            val delta = antennaPos - otherAntennaPos

                            antinodes += generateAntiNodes(antennaPos, delta).take(1)
                            antinodes += generateAntiNodes(otherAntennaPos, -delta).take(1)
                        }
                    }

                    antinodes
                }
                .filter { it.x in 0 until antennaMap.width && it.y in 0 until antennaMap.height }
                .toSet()
                .count()
                .toLong()
        }


    override fun part2(input: String): Long = parseInput(input)
        .let { antennaMap ->
            antennaMap
                .antennas
                .flatMap { (_, antennaPositions) ->
                    val antinodes = mutableListOf<Position>()

                    for ((idx, antennaPos) in antennaPositions.withIndex()) {
                        for (otherIdx in (idx + 1 until antennaPositions.count())) {
                            val otherAntennaPos = antennaPositions[otherIdx]
                            val delta = antennaPos - otherAntennaPos

                            // antenna position is now an antinode too
                            antinodes += antennaPos
                            antinodes += generateAntiNodes(
                                antennaPos,
                                delta
                            ).takeWhile { it.x in 0 until antennaMap.width && it.y in 0 until antennaMap.height }

                            // antenna position is now an antinode too
                            antinodes += otherAntennaPos
                            antinodes += generateAntiNodes(
                                otherAntennaPos,
                                -delta
                            ).takeWhile { it.x in 0 until antennaMap.width && it.y in 0 until antennaMap.height }
                        }
                    }

                    antinodes
                }
                .toSet()
                .count()
                .toLong()
        }

    fun generateAntiNodes(antennaPosition: Position, delta: Position): Sequence<Position> =
        generateSequence(antennaPosition + delta) { prev -> prev + delta }


    @JvmInline
    value class Position(private val pair: Pair<Int, Int>) {

        val x
            get() = pair.first
        val y
            get() = pair.second

        constructor(x: Int, y: Int) : this(x to y)

        operator fun plus(other: Position) = Position(x + other.x, y + other.y)

        operator fun unaryMinus() = Position(-x, -y)
        operator fun minus(other: Position) = Position(x - other.x, y - other.y)

        operator fun times(multiple: Int) = Position(x * multiple, y * multiple)

    }

    data class AntennaMap(val width: Int, val height: Int, val antennas: Map<Char, List<Position>>)

    fun parseInput(input: String): AntennaMap {
        val antennas = mutableMapOf<Char, MutableList<Position>>()

        val lines = input.lines()
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                when (char) {
                    '.' -> {}
                    else -> {
                        antennas.merge(
                            char,
                            mutableListOf(Position(x, y))
                        ) { old, new ->
                            old.addAll(new)
                            return@merge old
                        }
                    }
                }
            }
        }


        return AntennaMap(lines.first().count(), lines.count(), antennas)
    }
}