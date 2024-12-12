package day11

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName
import kotlin.test.Ignore

@DisplayName("Day 11: Plutionian Pebbles")
object PlutonianPebblesTestSpec : AdventOfCodeSolutionTestSpec<Long> {

    override val classUnderTest: AdventOfCodeSolution<Long> = PlutonianPebblesSolution

    override val part1TestInputs: List<String> = listOf("125 17")
    override val expectedPart1Outputs: List<Long> = listOf(55_312)

    // no test value provided
    override val expectedPart2Outputs: List<Long> = listOf(0)

    @Ignore
    override fun testPart2() = super.testPart2()
}