package day15

import AdventOfCodeSolution
import utils.IntVec2d

typealias Position = IntVec2d

object WarehouseWoesSolution : AdventOfCodeSolution<Long> {

    override val day: Int = 15
    override val problemName: String = "Warehouse Woes"

    const val BOX: Char = 'O'
    const val WALL: Char = '#'
    const val ROBOT: Char = '@'
    const val EMPTY: Char = '.'

    override fun part1(input: String): Long {
        // Parse the input into the board and moves
        val (boardInput, movesInput) = input.split("\n\n", limit = 2)
        val board = boardInput.lines().map { it.toCharArray() }.toMutableList()
        val moves = movesInput.lines().joinToString("")

        // Find robot's initial position
        var robotX = 0
        var robotY = 0
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == '@') {
                    robotX = i
                    robotY = j
                }
            }
        }

        var robotPos = Position(robotX, robotY)
        moves.forEach { move ->
            val moveDelta = getDirection(move)

            val nextRobotPos = robotPos + moveDelta
            when (board[nextRobotPos.y][nextRobotPos.x]) {
                WALL -> return@forEach
                EMPTY -> {
                    board[nextRobotPos.y][nextRobotPos.x] = ROBOT
                    board[robotPos.y][robotPos.x] = EMPTY

                    robotPos = nextRobotPos
                }

                BOX -> {
                    var boxPos = nextRobotPos + moveDelta
                    // move along lines of boxes
                    while (board[boxPos.y][boxPos.x] == BOX) {
                        boxPos = boxPos + moveDelta
                    }
                    // ensure the found position is empty -> otherwise we ran into a wall
                    if (board[boxPos.y][boxPos.x] == EMPTY) {
                        // move boxes
                        board[boxPos.y][boxPos.x] = BOX
                        // move robot
                        board[nextRobotPos.y][nextRobotPos.x] = ROBOT
                        board[robotPos.y][robotPos.x] = EMPTY
                        robotPos = nextRobotPos
                    }
                }
            }
        }

        return board.indices.sumOf { y -> board[y].indices.sumOf { x -> if (board[y][x] == BOX) 100L * y + x else 0 } }
    }

    fun getDirection(char: Char) = when (char) {
        '^' -> Position(0, -1)
        'v' -> Position(0, 1)
        '<' -> Position(-1, 0)
        '>' -> Position(1, 0)
        else -> throw IllegalArgumentException("Invalid direction: $char")
    }


    override fun part2(input: String): Long {
        TODO("Not yet implemented")
    }
}