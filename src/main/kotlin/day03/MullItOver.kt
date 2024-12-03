package day03

import readResource

fun main() {
    val input = readResource("day03/input.txt")

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun part1(input: String): Int = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
    .findAll(input)
    .map { match ->
        val (a, b) = match.destructured
        a.toInt() * b.toInt()
    }
    .sum()

fun part2(input: String): Int {
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