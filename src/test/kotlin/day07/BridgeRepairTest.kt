package day07

import readResource
import kotlin.test.Test
import kotlin.test.assertEquals

class BridgeRepairTest {

    @Test
    fun testPart1() {
        assertEquals(3749, part1(readResource("day07/testInput.txt")))
    }

    @Test
    fun testPart2() {
        assertEquals(11387, part2(readResource("day07/testInput.txt")))
    }
}