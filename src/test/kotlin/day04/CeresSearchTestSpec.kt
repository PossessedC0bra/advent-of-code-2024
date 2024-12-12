package day04

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 4: Ceres Search")
object CeresSearchTestSpec : AdventOfCodeSolutionTestSpec<Int> {

    override val classUnderTest: AdventOfCodeSolution<Int> = CeresSearchSolution

    override val part1TestInputs: List<String> = listOf(
        """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent()
    )
    override val expectedPart1Outputs: List<Int> = listOf(18)

    override val expectedPart2Outputs: List<Int> = listOf(9)

}