package day14

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

@DisplayName("Day 14: Restroom Redoubt")
object RestroomRedoubtSolutionTestSpec : AdventOfCodeSolutionTestSpec<Long> {

    override val classUnderTest: AdventOfCodeSolution<Long> = RestroomRedoubtSolution

    override val part1TestInputs: List<String> = listOf(
        """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3
        """.trimIndent()
    )
    override val expectedPart1Outputs: List<Long> = listOf(12)

    @TestFactory
    override fun testPart1(): List<DynamicTest> = part1TestInputs.zip(expectedPart1Outputs)
        .mapIndexed { idx, (i, o) ->
            DynamicTest.dynamicTest("Test ${idx + 1}") {
                assertEquals(
                    o,
                    RestroomRedoubtSolution.part1(i, 11, 7, 100)
                )
            }
        }

    override val expectedPart2Outputs: List<Long> = listOf()
}