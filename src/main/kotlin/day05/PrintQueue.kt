package day05

import readResource

fun main() {
    val input = readResource("day05/input.txt")

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun part1(input: String): Int {
    val mappings = input
        .lines()
        .takeWhile { it.isEmpty() }
        .map {
            val numbers = it.split('|').map { it.toInt() }
            numbers[0] to numbers[1]
        }
        .groupBy({ it.first }) { it.second }

    val reports = input.lines()
        .dropWhile { it.isNotEmpty() }
        .drop(1)
        .map { it.split(',').map { it.toInt() } }

    var result = 0
    for (report in reports) {
        run reports@{
            for (i in report.indices) {
                val num = report[i]
                if (!mappings.getOrElse(num) { listOf<Int>() }.any { report.take(i).contains(num) }) {
                    return@reports
                }
            }
            result += report[report.count() / 2]
        }
    }
    return result
}

fun part2(input: String): Int = 0