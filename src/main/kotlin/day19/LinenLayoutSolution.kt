package day19

import AdventOfCodeSolution

object LinenLayoutSolution : AdventOfCodeSolution<Int> {

    override val day: Int = 19
    override val problemName: String = "Linen Layout"

    override fun part1(input: String): Int {
        val (towelPatterns, designs) = parseInput(input)
        return designs.count { countNumberOfWaysToMakeDesignRecursive(it, towelPatterns) }
    }

    override fun part2(input: String): Long {
        val (towelPatterns, designs) = parseInput(input)
        return designs.sumOf { countNumberOfWaysToMakeDesign(it, towelPatterns) }
    }

    fun parseInput(input: String): Pair<List<String>, List<String>> {
        val (patterns, towels) = input.split("\n\n")
        return patterns.split(',').map { it.trim() } to towels.lines()
    }

    fun countNumberOfWaysToMakeDesignRecursive(design: String, patterns: List<String>): Boolean =
        isDesignPossibleRecursive(design, patterns, mutableMapOf())

    fun isDesignPossibleRecursive(design: String, patterns: List<String>, cache: MutableMap<String, Boolean>): Boolean {
        if (design.isEmpty()) {
            return true
        }

        return cache.getOrPut(design) {
            patterns.any {
                design.startsWith(it) && isDesignPossibleRecursive(
                    design.substring(it.length), patterns, cache
                )
            }
        }
    }

    fun countNumberOfWaysToMakeDesign(design: String, patterns: List<String>): Long =
        countNumberOfWaysToMakeDesignRecursive(design, patterns, mutableMapOf())

    fun countNumberOfWaysToMakeDesignRecursive(
        design: String,
        patterns: List<String>,
        cache: MutableMap<String, Long>,
    ): Long {
        if (design.isEmpty()) {
            return 1
        }

        return cache.getOrPut(design) {
            patterns.sumOf {
                when {
                    design.startsWith(it) -> countNumberOfWaysToMakeDesignRecursive(
                        design.substring(it.length),
                        patterns,
                        cache
                    )

                    else -> 0
                }
            }
        }
    }
}