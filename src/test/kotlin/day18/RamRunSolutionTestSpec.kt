package day18

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 18: RAM Run")
object RamRunSolutionTestSpec : AdventOfCodeSolutionTestSpec<Int> {

    override val classUnderTest: AdventOfCodeSolution<Int> = RamRunSolution

    override val part1TestInputs: List<String> = listOf(
        """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
        """.trimIndent()
    )
    override val expectedPart1Outputs: List<Int> = listOf(22)
    override fun functionToTestPart1(): (String) -> Int =
        { input -> (classUnderTest as RamRunSolution).part1(input, 6, 6, 12) }

    override val expectedPart2Outputs: List<String> = listOf("6,1")
    override fun functionToTestPart2(): (String) -> String =
        { input -> (classUnderTest as RamRunSolution).part2(input, 6, 6) }
}