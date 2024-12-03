package day03

import println
import readInput
import kotlin.io.path.Path
import kotlin.io.path.readText

// regex to extract strings of format mul(x,y), where x and y are numbers
private val pattern = Regex("""mul\((-?\d+),(-?\d+)\)""")
private val doPattern = Regex("""do\(\)""")

fun main() {
    fun part1(input: List<String>): Int {
        val matches = input.flatMap { pattern.findAll(it).map { match -> match.value } }

        return matches.fold(0) { acc, operation -> acc + execute(operation) }
    }

    fun part2(input: String): Int {
        val linesToKeep = input.split(doPattern).map { removeDisabled(it) }

        return part1(linesToKeep)
    }

    val input = readInput("src/day03/input.txt")
    val inputAsText = Path("src/day03/input.txt").readText()

    part1(input).println()
    part2(inputAsText).println()
}

private fun execute(operation: String): Int = pattern.matchEntire(operation)
    ?.destructured?.let { (x, y) -> x.toInt() * y.toInt() }
    ?: throw IllegalArgumentException("Operation could not be executed.")

private fun removeDisabled(line: String): String {
    val endIndex = line.indexOf("don't()")

    return if (endIndex != -1) {
        line.substring(0, endIndex)
    } else line
}