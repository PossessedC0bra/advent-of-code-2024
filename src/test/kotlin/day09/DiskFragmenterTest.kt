package day09

import readResource
import kotlin.test.Test
import kotlin.test.assertEquals

class DiskFragmenterTest {

    @Test
    fun testPart1() {
        assertEquals(1928, part1(readResource("day09/testInput.txt")))
    }

    @Test
    fun testPart2() {
        assertEquals(2858, part2(readResource("day09/testInput.txt")))
    }
}