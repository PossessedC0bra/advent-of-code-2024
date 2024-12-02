package day02

import readResource
import kotlin.math.absoluteValue

fun main() {
    val input = readResource("day02/input.txt")

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun part1(input: String): Int = input
    .lines()
    .map { line -> line.split("\\s+".toRegex()).map { it.toInt() } }
    .map { report -> if (isSafe(report)) 1 else 0 }
    .sum()

fun part2(input: String): Int = input
    .lines()
    .map { line -> line.split("\\s+".toRegex()).map { it.toInt() } }
    .map { report ->
        for (i in 0..report.lastIndex) {
            if (isSafe(report.toMutableList().apply { removeAt(i) })) {
                return@map 1
            }
        }

        return@map 0
    }
    .sum()

fun isSafe(report: List<Int>): Boolean {
    var isSafe = true
    var isIncreasing = true
    var isDecreasing = true

    var idx = 0
    while ((isSafe && (isIncreasing || isDecreasing)) && idx + 1 < report.count()) {
        val l = report[idx]
        val r = report[idx + 1]

        isSafe = ((l - r).absoluteValue <= 3)
        when {
            l < r -> isDecreasing = false
            l > r -> isIncreasing = false
            else -> {
                isIncreasing = false
                isDecreasing = false
            }
        }

        idx++
    }

    return isSafe && (isIncreasing || isDecreasing)
}

