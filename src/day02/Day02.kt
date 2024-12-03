package day02

import lineToIntegers
import println
import readInput

fun main() {
    fun part1(reports: List<List<Int>>): Int = reports.fold(0) { acc, report -> acc + analyze(report) }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("src/day02/input.txt").map { lineToIntegers(it) }

    part1(input).println()
}

private fun analyze(report: List<Int>): Int {
    val type = type(report)

    if (type == Type.NOT_SAFE) return 0

    return if (report.zipWithNext().all { (current, next) -> current.isSafeDifference(next, type) }) 1 else 0
}

private fun type(report: List<Int>): Type = when {
    report.size <= 1 -> Type.NOT_SAFE
    report[0] < report[1] -> Type.INCREASING
    report[0] > report[1] -> Type.DECREASING
    else -> Type.NOT_SAFE
}

private fun Int.isSafeDifference(next: Int, type: Type): Boolean = when (type) {
    Type.INCREASING -> (next - this) in 1..3
    Type.DECREASING -> (this - next) in 1..3
    else -> false
}

enum class Type {
    INCREASING, DECREASING, NOT_SAFE
}

