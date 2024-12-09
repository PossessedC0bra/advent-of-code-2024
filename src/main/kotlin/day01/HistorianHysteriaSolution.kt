package day01

import AdventOfCodeSolution
import kotlin.math.absoluteValue

object HistorianHysteriaSolution : AdventOfCodeSolution<Int> {

    override val day: Int = 1
    override val problemName: String = "Historian Hysteria"

    override fun part1(input: String): Int {
        val (leftList, rightList) = readLists(input)

        return leftList.sorted()
            .zip(rightList.sorted())
            .sumOf { (l, r) -> (l - r).absoluteValue }
    }

    override fun part2(input: String): Int {
        val (leftList, rightList) = readLists(input)

        val occurrences: Map<Int, Int> = rightList
            .groupBy { it }
            .mapValues { it.value.count() }

        return leftList.sumOf { it * (occurrences[it] ?: 0) }
    }

    fun readLists(input: String) = input
        .lines()
        .map {
            val numbers = it.split("""\s+""".toRegex())
            numbers[0].toInt() to numbers[1].toInt()
        }.unzip()
}