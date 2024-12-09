package day02

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 2: Red-Nosed Reports")
object RedNosedReportsTestSpec : AdventOfCodeSolutionTestSpec<Int> {

    override val classUnderTest: AdventOfCodeSolution<Int> = RedNosedReportsSolution

    override val part1TestInput: String = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

    override val expectedPart1Output: Int = 2

    override val expectedPart2Output: Int = 4

}