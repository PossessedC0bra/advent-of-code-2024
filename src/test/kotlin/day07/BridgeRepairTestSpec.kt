package day07

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 7: Bridge Repair")
object BridgeRepairTestSpec : AdventOfCodeSolutionTestSpec<Long> {

    override val classUnderTest: AdventOfCodeSolution<Long> = BridgeRepairSolution

    override val part1TestInput: String = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """.trimIndent()
    override val expectedPart1Output: Long = 3_749

    override val expectedPart2Output: Long = 11_387

}