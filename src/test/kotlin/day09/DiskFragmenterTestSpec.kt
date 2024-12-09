package day09

import AdventOfCodeSolution
import AdventOfCodeSolutionTestSpec
import org.junit.jupiter.api.DisplayName

@DisplayName("Day 9: Disk Fragmenter")
object DiskFragmenterTestSpec : AdventOfCodeSolutionTestSpec<Long> {

    override val classUnderTest: AdventOfCodeSolution<Long> = DiskFragmenterSolution

    override val part1TestInput: String = "2333133121414131402"
    override val expectedPart1Output: Long = 1_928

    override val expectedPart2Output: Long = 2_858

}