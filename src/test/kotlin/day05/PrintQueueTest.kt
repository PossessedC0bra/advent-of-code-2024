package day05

import readResource
import kotlin.test.Test
import kotlin.test.assertEquals

class PrintQueueTest {

    @Test
    fun testPart1() {
        assertEquals(143, part1(readResource("day05/testInput.txt")))
    }

    @Test
    fun testPart2() {
        assertEquals(123, part2(readResource("day05/testInput.txt")))
    }
}