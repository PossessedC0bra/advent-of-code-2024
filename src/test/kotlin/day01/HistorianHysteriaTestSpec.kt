package day01

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 1: Historian Hysteria")
object HistorianHysteriaTestSpec : AdventOfCodeSolutionTestSpec<Int> {

    override val classUnderTest: AdventOfCodeSolution<Int> = HistorianHysteriaSolution

    override val part1TestInputs: List<String> = listOf(
        """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent()
    )
    override val expectedPart1Outputs: List<Int> = listOf(11)

    override val expectedPart2Outputs: List<Int> = listOf(31)

}