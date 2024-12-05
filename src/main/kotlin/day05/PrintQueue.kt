package day05

import readResource

fun main() {
    val input = readResource("day05/input.txt")

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun part1(input: String): Int =
    parsePageOrderRulesAndManuals(input)
        .let { (pageOrderRules, safetyManuals) ->
            return safetyManuals
                .filter { isManualValid(it, pageOrderRules) }
                .sumOf { it.middle() }
        }

fun part2(input: String): Int =
    parsePageOrderRulesAndManuals(input)
        .let { (pageOrderRules, safetyManuals) ->
            safetyManuals
                .filterNot { isManualValid(it, pageOrderRules) }
                .map { fixManual(it, pageOrderRules) }
                .sumOf { it.middle() }
        }

fun parsePageOrderRulesAndManuals(input: String): Pair<Map<Int, Set<Int>>, List<List<Int>>> =
    input.split("\n\n")
        .let { (rulesString, manualsString) ->
            val pageOrderRules: Map<Int, Set<Int>> = rulesString
                .lines()
                .map { it.split('|').map { it.toInt() }.let { (num, comesAfter) -> num to comesAfter } }
                .groupBy({ it.first }, { it.second })
                .mapValues { it.value.toSet() }

            val safetyManuals = manualsString
                .lines()
                .map { line -> line.split(',').map { it.toInt() } }

            pageOrderRules to safetyManuals
        }

fun isManualValid(manual: List<Int>, pageOrderRules: Map<Int, Set<Int>>): Boolean =
    manual.indices.all { i -> manual.subList(0, i).none { pageOrderRules[manual[i]]?.contains(it) == true } }

fun fixManual(manual: List<Int>, pageOrderRules: Map<Int, Set<Int>>): List<Int> =
    manual.sortedWith { a, b ->
        when {
            pageOrderRules[b]?.contains(a) == true -> -1
            else -> 1
        }
    }

fun <T> List<T>.middle(): T {
    return this[this.size / 2]
}