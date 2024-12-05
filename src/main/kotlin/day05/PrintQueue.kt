package day05

import readResource

fun main() {
    val input = readResource("day05/input.txt")

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun part1(input: String): Int {
    val numberToNumbersThatMustAppearAfterMapping = input
        .lines()
        .takeWhile { it.isNotEmpty() }
        .map {
            val numbers = it.split('|').map { it.toInt() }
            numbers[0] to numbers[1]
        }
        .groupBy({ it.first }) { it.second }

    val reports = input.lines()
        .dropWhile { it.isNotEmpty() }
        .drop(1)
        .map { line -> line.split(',').map { it.toInt() } }

    var result = 0
    reports.forEach { report ->
        for (i in report.indices) {
            numberToNumbersThatMustAppearAfterMapping[report[i]]?.let { numbersMustAppearAfterX ->
                if (report.take(i).any { numbersMustAppearAfterX.contains(it) }) {
                    return@forEach
                }
            }
        }
        result += report[report.size / 2]
    }
    return result
}

fun part2(input: String): Int = 0