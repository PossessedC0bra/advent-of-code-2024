import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

interface AdventOfCodeSolutionTestSpec<Out> {

    val classUnderTest: AdventOfCodeSolution<Out>

    val part1TestInputs: List<String>
    val expectedPart1Outputs: List<Out>

    val part2TestInputs: List<String>
        get() = part1TestInputs
    val expectedPart2Outputs: List<Out>

    @TestFactory
    fun testPart1(): List<DynamicTest> = part1TestInputs.zip(expectedPart1Outputs)
        .mapIndexed { idx, (input, output) -> createDynamicTest(idx, input, classUnderTest::part1, output) }

    @TestFactory
    fun testPart2(): List<DynamicTest> = part2TestInputs.zip(expectedPart2Outputs)
        .mapIndexed { idx, (input, output) -> createDynamicTest(idx, input, classUnderTest::part2, output) }

    private fun createDynamicTest(idx: Int, input: String, functionToTest: (String) -> Out, output: Out) =
        DynamicTest.dynamicTest("Test ${idx + 1}") { assertEquals(output, functionToTest(input)) }
}
