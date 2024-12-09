package day04

import AdventOfCodeSolution

object CeresSearchSolution : AdventOfCodeSolution<Int> {

    override val day: Int = 4
    override val problemName: String = "Ceres Search"

    override fun part1(input: String): Int {
        val board = Board(input)
        val word = "XMAS".toCharArray()

        var result = 0
        for (y in 0..<board.height) {
            for (x in 0..<board.width) {
                for (axis in Axis.entries) {
                    if (findWordBidirectional(word, axis, board, x, y)) {
                        result++
                    }
                }
            }
        }

        return result
    }

    override fun part2(input: String): Int {
        val board = Board(input)
        val word = "MAS".toCharArray()

        var result = 0
        for (y in 1..<board.height - 1) {
            for (x in 1..<board.width - 1) {
                when (board[x, y]) {
                    'A' -> if (findWordBidirectional(word, Axis.DIAGONAL_DOWN_LEFT, board, x + 1, y - 1)
                        && findWordBidirectional(word, Axis.DIAGONAL_DOWN_RIGHT, board, x - 1, y - 1)
                    ) {
                        result++
                    }

                    else -> {}
                }
            }
        }

        return result
    }

    fun findWordBidirectional(
        word: CharArray, axis: Axis, board: Board, x: Int, y: Int,
    ): Boolean {
        if (axis.shiftX(x, word.size - 1) !in 0..<board.width
            || axis.shiftY(y, word.size - 1) !in 0..<board.height
        ) {
            return false
        }

        val wordBackward = word.reversedArray()

        var idx = 0
        var matchesForward = true
        var matchesBackward = true
        while ((matchesForward || matchesBackward) && idx < word.size) {
            val newX = axis.shiftX(x, idx)
            val newY = axis.shiftY(y, idx)

            if (board[newX, newY] != word[idx]) {
                matchesForward = false
            }
            if (board[newX, newY] != wordBackward[idx]) {
                matchesBackward = false
            }

            idx++
        }

        return matchesForward || matchesBackward
    }

    class Board {
        private val charBuffer: CharArray
        val width: Int
        val height: Int

        constructor(input: String) {
            val rows = input.lines()
            require(rows.isNotEmpty()) { "Input must contain at least one row." }
            val firstRowLength = rows.first().length
            require(rows.all { it.length == firstRowLength }) {
                "All rows must have the same length."
            }

            this.width = firstRowLength
            this.height = rows.size
            this.charBuffer = rows.joinToString("").toCharArray()
        }

        operator fun get(x: Int, y: Int): Char {
            require(x in 0 until width) { "x coordinate out of bounds: $x" }
            require(y in 0 until height) { "y coordinate out of bounds: $y" }

            return charBuffer[y * width + x]
        }

        override fun toString(): String {
            return buildString {
                for (y in 0 until height) {
                    append(charBuffer.slice(y * width until (y + 1) * width).joinToString(""))
                    append("\n")
                }
            }
        }
    }

    enum class Axis(val shiftX: (Int, Int) -> Int, val shiftY: (Int, Int) -> Int) {
        HORIZONTAL({ x, shift -> x + shift }, { y, _ -> y }),
        VERTICAL({ x, _ -> x }, { y, shift -> y + shift }),
        DIAGONAL_DOWN_LEFT({ x, shift -> x - shift }, { y, shift -> y + shift }),
        DIAGONAL_DOWN_RIGHT({ x, shift -> x + shift }, { y, shift -> y + shift }),
    }
}