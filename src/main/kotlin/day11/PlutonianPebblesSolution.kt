package day11

import AdventOfCodeSolution
import kotlin.math.log10
import kotlin.math.pow

object PlutonianPebblesSolution : AdventOfCodeSolution<Long> {

    override val day: Int = 11
    override val problemName: String = "Plutonian Pebbles"

    override fun part1(input: String): Long = blink(
        input.split(' ').map { it.toLong() },
        25
    )

    override fun part2(input: String): Long = blink(
        input.split(' ').map { it.toLong() },
        75
    )

    fun blink(stones: List<Long>, count: Int): Long {
        val memory = mutableMapOf<BlinkCacheKey, Long>()
        return stones.sumOf { blinkRecursive(it, count, memory) }
    }

    fun blinkRecursive(stone: Long, remainingBlinkCount: Int, memory: MutableMap<BlinkCacheKey, Long>): Long {
        if (remainingBlinkCount == 0) {
            return 1
        }

        return memory.getOrPut(BlinkCacheKey(stone, remainingBlinkCount)) {
            when {
                stone == 0L -> blinkRecursive(1, remainingBlinkCount - 1, memory)
                else -> {
                    val numDigits = log10(stone.toDouble()).toInt() + 1
                    if (numDigits % 2 == 0) {
                        val divisor = 10.0.pow(numDigits / 2).toLong()

                        blinkRecursive(
                            stone / divisor,
                            remainingBlinkCount - 1,
                            memory
                        ) + blinkRecursive(
                            stone % divisor,
                            remainingBlinkCount - 1,
                            memory
                        )
                    } else {
                        blinkRecursive(stone * 2024, remainingBlinkCount - 1, memory)
                    }
                }
            }
        }
    }

    data class BlinkCacheKey(val stone: Long, val blinkCount: Int)
}