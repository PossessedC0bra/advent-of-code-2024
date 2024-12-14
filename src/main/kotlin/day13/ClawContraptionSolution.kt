package day13

import AdventOfCodeSolution

object ClawContraptionSolution : AdventOfCodeSolution<Long> {

    override val day: Int = 13
    override val problemName: String = "Claw Contraption"

    val MACHINE_REGEX = """
        Button A: X\+(\d+), Y\+(\d+)
        Button B: X\+(\d+), Y\+(\d+)
        Prize: X=(\d+), Y=(\d+)
        """
        .trimIndent()
        .toRegex()

    override fun part1(input: String): Long = parseInput(input)
        .map { (buttonA, buttonB, prize) ->
            solveLinearSystemOfDiophantineEquations(
                buttonA.x, buttonB.x, prize.x,
                buttonA.y, buttonB.y, prize.y
            )
        }
        .filterNotNull()
        .sumOf { (a, b) -> (3 * a) + (1 * b) }

    override fun part2(input: String): Long = parseInput(input)
        .map { (buttonA, buttonB, prize) ->
            solveLinearSystemOfDiophantineEquations(
                buttonA.x, buttonB.x, prize.x + 10_000_000_000_000,
                buttonA.y, buttonB.y, prize.y + 10_000_000_000_000
            )
        }
        .filterNotNull()
        .sumOf { (a, b) -> (3 * a) + (1 * b) }

    fun parseInput(input: String): Sequence<Triple<Position, Position, Position>> = MACHINE_REGEX
        .findAll(input)
        .map {
            val (aX, aY, bX, bY, prizeX, prizeY) = it.destructured
            Triple(
                Position(aX.toLong(), aY.toLong()),
                Position(bX.toLong(), bY.toLong()),
                Position(prizeX.toLong(), prizeY.toLong())
            )
        }

    /**
     * Solves a linear system of Diophantine equations with two unknowns:
     * a1 * x + b1 * y = c1
     * a2 * x + b2 * y = c2
     *
     * @param a1 Coefficient of x in the first equation.
     * @param b1 Coefficient of y in the first equation.
     * @param c1 Constant term in the first equation.
     * @param a2 Coefficient of x in the second equation.
     * @param b2 Coefficient of y in the second equation.
     * @param c2 Constant term in the second equation.
     * @return A pair of integers (x, y) if a solution exists; null otherwise.
     */
    fun solveLinearSystemOfDiophantineEquations(
        a1: Long, b1: Long, c1: Long,
        a2: Long, b2: Long, c2: Long,
    ): Pair<Long, Long>? {
        val determinant = a1 * b2 - a2 * b1
        if (determinant == 0L) {
            return null // no solution
        }

        val xNumerator = c1 * b2 - c2 * b1
        val yNumerator = a1 * c2 - a2 * c1
        if (xNumerator % determinant != 0L || yNumerator % determinant != 0L) {
            return null // no integer solution
        }

        return (xNumerator / determinant) to (yNumerator / determinant)
    }

    data class Position(val x: Long, val y: Long)
}