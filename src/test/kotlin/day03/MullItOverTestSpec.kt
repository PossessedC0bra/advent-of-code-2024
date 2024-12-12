package day03

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 3: Mull It Over")
object MullItOverTestSpec : AdventOfCodeSolutionTestSpec<Int> {

    override val classUnderTest: AdventOfCodeSolution<Int> = MullItOverSolution

    override val part1TestInputs: List<String> =
        listOf("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")
    override val expectedPart1Outputs: List<Int> = listOf(161)

    override val part2TestInputs: List<String> =
        listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")
    override val expectedPart2Outputs: List<Int> = listOf(48)

}