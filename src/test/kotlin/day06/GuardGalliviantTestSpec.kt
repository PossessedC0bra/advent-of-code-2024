package day06

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 6: Guard Gallivant")
object GuardGalliviantTestSpec : AdventOfCodeSolutionTestSpec<Int> {

    override val classUnderTest: AdventOfCodeSolution<Int> = GuardGallivantSolution

    override val part1TestInputs: List<String> = listOf(
        """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """.trimIndent()
    )
    override val expectedPart1Outputs: List<Int> = listOf(41)

    override val expectedPart2Outputs: List<Int> = listOf(6)

}