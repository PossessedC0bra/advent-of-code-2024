package day17

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 17: Chronospatial Computer")
object ChronospatialComputerSolutionTestSpec : AdventOfCodeSolutionTestSpec<String> {

    override val classUnderTest: AdventOfCodeSolution<String> = ChronospatialComputerSolution

    override val part1TestInputs: List<String> = listOf(
        """
            Register A: 729
            Register B: 0
            Register C: 0

            Program: 0,1,5,4,3,0
        """.trimIndent()
    )
    override val expectedPart1Outputs: List<String> = listOf("4,6,3,5,6,3,5,2,1,0")

    override val part2TestInputs: List<String> = listOf(
        """
            Register A: 2024
            Register B: 0
            Register C: 0

            Program: 0,3,5,4,3,0
        """.trimIndent()
    )
    override val expectedPart2Outputs: List<String> = listOf("117_440") // TODO ykl: support for multi typed results
}