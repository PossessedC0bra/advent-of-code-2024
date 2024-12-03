package day02

import readResource
import kotlin.test.Test
import kotlin.test.assertEquals

class RedNosedReportsTest {

    @Test
    fun testPart1() {
        assertEquals(2, part1(readResource("day02/testInput.txt")))
    }

    @Test
    fun testPart2() {
        assertEquals(4, part2(readResource("day02/testInput.txt")))
    }
}