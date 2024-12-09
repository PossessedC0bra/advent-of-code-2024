package day07

import AdventOfCodeSolution
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

object BridgeRepairSolution : AdventOfCodeSolution<Long> {

    override val day: Int = 7
    override val problemName: String = "Bridge Repair"

    override fun part1(input: String): Long = parseInput(input)
        .filter { canBeSolved(it, arrayOf(BinaryOperators.PLUS, BinaryOperators.MULTIPLY)) }
        .sumOf { it.solution }


    override fun part2(input: String): Long = parseInput(input)
        .filter {
            canBeSolved(
                it,
                arrayOf(BinaryOperators.PLUS, BinaryOperators.MULTIPLY, BinaryOperators.CONCATENATION)
            )
        }
        .sumOf { it.solution }

    enum class BinaryOperators(val eval: (Long, Long) -> Long) {
        PLUS(Long::plus),
        MULTIPLY(Long::times),
        CONCATENATION({ a, b -> (a * 10.0.pow(1 + floor(log10(b.toDouble())))).toLong() + b })
    }

    data class Equation(val solution: Long, val operands: List<Long>)

    fun parseInput(input: String): List<Equation> = input
        .lines()
        .associate { equation ->
            equation
                .split(": ")
                .let { it[0] to it[1] }
        }
        .map { (result, operands) -> Equation(result.toLong(), operands.split(' ').map(String::toLong)) }

    fun canBeSolved(equation: Equation, availableOperators: Array<BinaryOperators>) =
        canBeSolved(
            equation,
            availableOperators,
            0,
            equation.operands.first()
        )

    fun canBeSolved(
        equation: Equation,
        availableOperators: Array<BinaryOperators>,
        operatorCount: Int,
        currentResult: Long,
    ): Boolean =
        when {
            operatorCount == equation.operands.count() - 1 -> currentResult == equation.solution
            currentResult > equation.solution -> false
            else -> availableOperators.any {
                canBeSolved(
                    equation,
                    availableOperators,
                    operatorCount + 1,
                    it.eval(currentResult, equation.operands[operatorCount + 1])
                )
            }
        }
}