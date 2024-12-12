package day10

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 10: Hoof It")
object HoofItTestSpec : AdventOfCodeSolutionTestSpec<Long> {

    override val classUnderTest: AdventOfCodeSolution<Long> = HoofItSolution

    override val part1TestInputs: List<String> = listOf(
        """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent()
    )
    override val expectedPart1Outputs: List<Long> = listOf(36)

    override val expectedPart2Outputs: List<Long> = listOf(81)
}