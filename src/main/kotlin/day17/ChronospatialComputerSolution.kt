package day17

import AdventOfCodeSolution
import kotlin.math.pow

object ChronospatialComputerSolution : AdventOfCodeSolution<String> {

    override val day: Int = 17
    override val problemName: String = "Chronospatial Computer"

    override fun part1(input: String): String {
        val (initialMachine, instructions) = parseInput(input)

        var machine = initialMachine
        while (machine.instructionPointer < instructions.count()) {
            val instruction = Instruction.entries[instructions[machine.instructionPointer]]
            machine = instruction.evaluate(machine, Operand(instructions[machine.instructionPointer + 1]))
        }

        return machine.out.joinToString(",")
    }

    override fun part2(input: String): String {
        TODO("Not yet implemented")
    }

    val MACHINE_REGEX = """
        Register A: (\d+)
        Register B: (\d+)
        Register C: (\d+)

        Program: ([\d,]+)
    """.trimIndent().toRegex()

    fun parseInput(input: String): Pair<ChronospatialComputer, List<Int>> = MACHINE_REGEX
        .find(input)
        .let { match ->
            val (registerA, registerB, registerC, program) = match!!.destructured

            ChronospatialComputer(
                0,
                registerA.toInt(),
                registerB.toInt(),
                registerC.toInt(),
                listOf()
            ) to program
                .split(',')
                .map { it.first().digitToInt() }
        }

    data class ChronospatialComputer(
        val instructionPointer: Int,
        val registerA: Int,
        val registerB: Int,
        val registerC: Int,
        val out: List<Int>
    )

    @JvmInline
    value class Operand(private val value: Int) {

        fun literalOperand(): Int = this.value

        fun comboOperand(computer: ChronospatialComputer): Int = when (this.value) {
            0, 1, 2, 3 -> literalOperand()
            4 -> computer.registerA
            5 -> computer.registerB
            6 -> computer.registerC
            7 -> throw RuntimeException("Operand 7 is reserved and should not appear in a valid program")
            else -> throw RuntimeException("Operand has more than 4 Bits")
        }
    }

    enum class Instruction(val evaluate: (machine: ChronospatialComputer, operand: Operand) -> ChronospatialComputer) {
        ADV({ machine, operand ->
            machine.copy(
                instructionPointer = machine.instructionPointer + 2,
                registerA = machine.registerA / 2.0.pow(operand.comboOperand(machine)).toInt()
            )
        }),
        BXL({ machine, operand ->
            machine.copy(
                instructionPointer = machine.instructionPointer + 2,
                registerB = machine.registerB xor operand.literalOperand()
            )
        }),
        BST({ machine, operand ->
            machine.copy(
                instructionPointer = machine.instructionPointer + 2,
                registerB = operand.comboOperand(machine) % 8
            )
        }),
        JNZ({ machine, operand ->
            machine.copy(
                instructionPointer = if (machine.registerA != 0) operand.literalOperand() else machine.instructionPointer + 2
            )
        }),
        BXC({ machine, _ ->
            machine.copy(
                instructionPointer = machine.instructionPointer + 2,
                registerB = machine.registerB xor machine.registerC
            )
        }),
        OUT({ machine, operand ->
            machine.copy(
                instructionPointer = machine.instructionPointer + 2,
                out = machine.out + (operand.comboOperand(machine) % 8)
            )

        }),
        BDV({ machine, operand ->
            machine.copy(
                instructionPointer = machine.instructionPointer + 2,
                registerB = machine.registerA / 2.0.pow(operand.comboOperand(machine)).toInt()
            )
        }),
        CDV({ machine, operand ->
            machine.copy(
                instructionPointer = machine.instructionPointer + 2,
                registerC = machine.registerA / 2.0.pow(operand.comboOperand(machine)).toInt()
            )
        });
    }
}