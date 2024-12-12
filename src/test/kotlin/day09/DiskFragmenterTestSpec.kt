package day09

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 9: Disk Fragmenter")
object DiskFragmenterTestSpec : AdventOfCodeSolutionTestSpec<Long> {

    override val classUnderTest: AdventOfCodeSolution<Long> = DiskFragmenterSolution

    override val part1TestInputs: List<String> = listOf("2333133121414131402")
    override val expectedPart1Outputs: List<Long> = listOf(1_928)

    override val expectedPart2Outputs: List<Long> = listOf(2_858)
}