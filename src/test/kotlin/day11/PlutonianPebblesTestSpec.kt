package day11

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName
import kotlin.test.Ignore

@DisplayName("Day 11: Plutionian Pebbles")
object PlutonianPebblesTestSpec : AdventOfCodeSolutionTestSpec<Long> {

    override val classUnderTest: AdventOfCodeSolution<Long> = PlutonianPebblesSolution

    override val part1TestInput: String = "125 17"
    override val expectedPart1Output: Long = 55_312

    // no test value provided
    override val expectedPart2Output: Long = 0

    @Ignore
    override fun testPart2() {
        super.testPart2()
    }

}