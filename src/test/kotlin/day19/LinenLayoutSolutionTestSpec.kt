package day19

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 19: Linen Layout")
object LinenLayoutSolutionTestSpec : AdventOfCodeSolutionTestSpec<Int> {

    override val classUnderTest: AdventOfCodeSolution<Int> = LinenLayoutSolution

    override val part1TestInputs: List<String> = listOf(
        """
            r, wr, b, g, bwu, rb, gb, br

            brwrr
            bggr
            gbbr
            rrbgbr
            ubwu
            bwurrg
            brgr
            bbrgwb
        """.trimIndent()
    )
    override val expectedPart1Outputs: List<Int> = listOf(6)

    override val expectedPart2Outputs: List<Long> = listOf(16)
}