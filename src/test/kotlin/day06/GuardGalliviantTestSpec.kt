package day06

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 6: Guard Gallivant")
object GuardGalliviantTestSpec : AdventOfCodeSolutionTestSpec<Int> {

    override val classUnderTest: AdventOfCodeSolution<Int> = GuardGallivantSolution

    override val part1TestInput: String = """
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
    override val expectedPart1Output: Int = 41

    override val expectedPart2Output: Int = 6

}