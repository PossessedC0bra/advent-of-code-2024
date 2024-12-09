package day03

import AdventOfCodeSolution

object MullItOverSolution : AdventOfCodeSolution<Int> {

    override val day: Int = 3
    override val problemName: String = "Mull It Over"

    override fun part1(input: String): Int = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
        .findAll(input)
        .map { match ->
            val (a, b) = match.destructured
            a.toInt() * b.toInt()
        }
        .sum()

    override fun part2(input: String): Int {
        var result = 0

        var isEnabled = true
        var i = 0
        while (i < input.length) {
            when {
                input.startsWith("do()", i) -> {
                    isEnabled = true
                    i += 4
                }

                input.startsWith("don't()", i) -> {
                    isEnabled = false
                    i += 7
                }

                input.startsWith("mul(", i) -> {
                    i += 4
                    if (!isEnabled) {
                        continue
                    }

                    val closingParenthesisIdx = input.indexOf(')', i)
                    val operands = input.substring(i, closingParenthesisIdx).split(',')
                    if (operands.count() == 2) {
                        val l = operands[0].toIntOrNull() ?: continue
                        var r = operands[1].toIntOrNull() ?: continue
                        result += l * r

                        i = closingParenthesisIdx + 1
                    }
                }

                else -> i++
            }
        }

        return result
    }
}