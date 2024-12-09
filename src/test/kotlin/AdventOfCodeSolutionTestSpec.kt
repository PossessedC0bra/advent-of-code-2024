import kotlin.test.Test
import kotlin.test.assertEquals

interface AdventOfCodeSolutionTestSpec<Out> {

    val classUnderTest: AdventOfCodeSolution<Out>

    val part1TestInput: String
    val expectedPart1Output: Out

    val part2TestInput: String
        get() = part1TestInput
    val expectedPart2Output: Out

    @Test
    fun testPart1() {
        assertEquals(expectedPart1Output, classUnderTest.part1(part1TestInput))
    }

    @Test
    fun testPart2() {
        assertEquals(expectedPart2Output, classUnderTest.part2(part2TestInput))
    }
}
