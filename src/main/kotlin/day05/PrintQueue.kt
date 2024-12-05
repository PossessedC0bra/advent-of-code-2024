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

    val safetyManuals = input.lines()
        .dropWhile { it.isNotEmpty() }
        .drop(1)
        .map { line -> line.split(',').map { it.toInt() } }

    var result = 0
    safetyManuals.forEach { manual ->

        result += manual[manual.size / 2]
    }
    return result
}

fun part2(input: String): Int = parseInput(input).let { (pageOrderRules, safetyManuals) ->
    safetyManuals
        .filter { isManualValid(it, pageOrderRules) }
        .map { fixManual(it, pageOrderRules) }
        .map { it.middle() }
        .sum()
}

fun parseInput(input: String): Pair<Map<Int, Set<Int>>, List<List<Int>>> {
    val numberToNumbersThatMustAppearAfterMapping = input
        .lines()
        .takeWhile { it.isNotEmpty() }
        .map {
            val numbers = it.split('|').map { it.toInt() }
            numbers[0] to numbers[1]
        }
        .groupBy({ it.first }) { it.second }

    val safetyManuals = input.lines()
        .dropWhile { it.isNotEmpty() }
        .drop(1)
        .map { line -> line.split(',').map { it.toInt() } }

    numberToNumbersThatMustAppearAfterMapping to safetyManuals
}

fun isManualValid(manual: List<Int>, pageOrderRules: Map<Int, Set<Int>>): Boolean {
    for (i in manual.indices) {
        pageOrderRules[manual[i]]?.let { numbersMustAppearAfterX ->
            if (manual.take(i).any { numbersMustAppearAfterX.contains(it) }) {
                return false
            }
        }
    }

    return true
}

fun fixManual(manual: List<Int>): List<Int> {
    TODO("Implement")
    return manual
}

fun <T> List<T>.middle(): T {
    return this[this.size / 2]
}