package day05

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 5: Print Queue")
object PrintQueueTestSpec : AdventOfCodeSolutionTestSpec<Int> {

    override val classUnderTest: AdventOfCodeSolution<Int> = PrintQueueSolution

    override val part1TestInput: String = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13

        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent()
    override val expectedPart1Output: Int = 143

    override val expectedPart2Output: Int = 123

}