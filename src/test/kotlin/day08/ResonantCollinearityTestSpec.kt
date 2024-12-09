package day08

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 8: Resonant Collinearity")
object ResonantCollinearityTestSpec : AdventOfCodeSolutionTestSpec<Long> {

    override val classUnderTest: AdventOfCodeSolution<Long> = ResonantCollinearitySolution

    override val part1TestInput: String = """
        ............
        ........0...
        .....0......
        .......0....
        ....0.......
        ......A.....
        ............
        ............
        ........A...
        .........A..
        ............
        ............
    """.trimIndent()
    override val expectedPart1Output: Long = 14

    override val expectedPart2Output: Long = 34

}