import kotlin.time.Duration
import kotlin.time.TimeSource

interface AdventOfCodeSolution<T> {

    val day: Int
    val problemName: String

    val part1InputFileName: String
        get() = "day${day.toString().padStart(2, '0')}/input.txt"
    val part2InputFileName: String
        get() = part1InputFileName

    fun run() {
        println("--- Day $day: $problemName ---")
        println()

        val part1Input = readResource(part1InputFileName)
        // exclude file read time from the duration
        val startPart1TimeMark = TimeSource.Monotonic.markNow()
        val part1Output = part1(part1Input)
        val part1Duration = startPart1TimeMark.elapsedNow()
        println("Part 1: $part1Output (${part1Duration.toHmsString()})")

        val part2Input = readResource(part2InputFileName)
        // exclude file read time from the duration
        val startPart2TimeMark = TimeSource.Monotonic.markNow()
        val part2Output = part2(part2Input)
        val part2Duration = startPart2TimeMark.elapsedNow()
        println("Part 2: $part2Output (${part2Duration.toHmsString()})")

        println()
    }

    fun part1(input: String): Any

    fun part2(input: String): Any
}

fun Duration.toHmsString(): String = toComponents { minutes, seconds, nanoseconds ->
    buildString {
        if (minutes > 0) append("${minutes}m ")
        if (seconds > 0) append("${seconds}s ")
        if (nanoseconds > 0) append("${nanoseconds / 1_000_000}ms")
    }
}
