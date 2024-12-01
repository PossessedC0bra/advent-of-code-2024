package day01

import readResource
import kotlin.test.Test
import kotlin.test.assertEquals

class HistorianHysteriaTest {

    @Test
    fun testPart1() {
        assertEquals(11, part1(readResource("day01/input.txt")))
    }

    @Test
    fun testPart2() {
        assertEquals(31, part2(readResource("day01/input.txt")))
    }
}