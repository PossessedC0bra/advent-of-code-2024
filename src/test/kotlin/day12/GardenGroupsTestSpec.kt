package day12

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 12: Garden Groups")
object GardenGroupsTestSpec : AdventOfCodeSolutionTestSpec<Long> {

    override val classUnderTest: AdventOfCodeSolution<Long> = GardenGroups

    override val part1TestInputs: List<String> = listOf(
        """
            AAAA
            BBCD
            BBCC
            EEEC
    """.trimIndent(), """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
    """.trimIndent(), """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
    """.trimIndent()
    )
    override val expectedPart1Outputs: List<Long> = listOf(140, 772, 1930)

    override val part2TestInputs: List<String> = listOf(
        """
            AAAA
            BBCD
            BBCC
            EEEC
        """.trimIndent(), """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
    """.trimIndent(), """
            EEEEE
            EXXXX
            EEEEE
            EXXXX
            EEEEE
        """.trimIndent(), """
            AAAAAA
            AAABBA
            AAABBA
            ABBAAA
            ABBAAA
            AAAAAA
        """.trimIndent(), """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
    """.trimIndent()
    )
    override val expectedPart2Outputs: List<Long> = listOf(80, 436, 236, 368, 1206)
}