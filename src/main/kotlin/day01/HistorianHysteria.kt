package day01

import readResource
import kotlin.collections.map
import kotlin.collections.unzip
import kotlin.math.absoluteValue
import kotlin.text.lines
import kotlin.text.split
import kotlin.text.toInt
import kotlin.text.toRegex
import kotlin.to

fun main() {
    val input = readResource("day01/input.txt")

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun part1(input: String): Int {
    val (leftList, rightList) = readLists(input)

    return leftList.sorted()
        .zip(rightList.sorted())
        .sumOf { (l, r) -> (l - r).absoluteValue }
}

fun part2(input: String): Int {
    val (leftList, rightList) = readLists(input)

    val occurrences: Map<Int, Int> = rightList
        .groupBy { it }
        .mapValues { it.value.count() }

    return leftList
        .map { it * (occurrences[it] ?: 0) }
        .sum()
}

fun readLists(input: String) = input
    .lines()
    .map {
        val numbers = it.split("\\s+".toRegex())
        numbers[0].toInt() to numbers[1].toInt()
    }.unzip()

