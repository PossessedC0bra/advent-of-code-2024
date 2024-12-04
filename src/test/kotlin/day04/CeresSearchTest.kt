package day04

import readResource
import kotlin.test.Test
import kotlin.test.assertEquals

class CeresSearchTest {

    @Test
    fun testPart1() {
        assertEquals(18, part1(readResource("day04/testInput.txt")))
    }

    @Test
    fun testPart2() {
        assertEquals(9, part2(readResource("day04/testInput.txt")))
    }
}