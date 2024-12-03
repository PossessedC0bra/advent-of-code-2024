package day03

import readResource
import kotlin.test.Test
import kotlin.test.assertEquals

class MullItOverTest {

    @Test
    fun testPart1() {
        assertEquals(161, part1(readResource("day03/testInput1.txt")))
    }

    @Test
    fun testPart2() {
        assertEquals(48, part2(readResource("day03/testInput2.txt")))
    }
}