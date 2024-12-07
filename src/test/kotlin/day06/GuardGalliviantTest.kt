package day06

import readResource
import kotlin.test.Test
import kotlin.test.assertEquals

class GuardGalliviantTest {

    @Test
    fun part1Test() {
        assertEquals(41, part1(readResource("day06/testInput.txt")))
    }

    @Test
    fun part2Test() {
        assertEquals(6, part2(readResource("day06/testInput.txt")))
    }
}