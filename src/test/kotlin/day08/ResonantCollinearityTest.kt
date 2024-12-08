package day08

import readResource
import kotlin.test.Test
import kotlin.test.assertEquals

class ResonantCollinearityTest {

    @Test
    fun testPart1() {
        assertEquals(14, part1(readResource("day08/testInput.txt")))
    }

    @Test
    fun testPart2() {
        assertEquals(34, part2(readResource("day08/testInput.txt")))
    }
}