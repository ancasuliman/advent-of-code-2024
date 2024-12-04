package day04

import println
import readInput

fun main() {
    val input = readInput("src/day04/input.txt")
    val chars = input.map { it.toList() }

    fun isGood(word: String): Int = if (word == "XMAS" || word == "SAMX") 1 else 0

    fun horizontal(line: List<Char>, index: Int): Int {
        val wordForward = line.subList(index, (index + 4).coerceAtMost(line.size)).joinToString("")
        val wordBackward = line.subList((index - 3).coerceAtLeast(0), index + 1).joinToString("")
        return isGood(wordForward) + isGood(wordBackward)
    }

    fun vertical(i: Int, j: Int): Int {
        val column = chars.mapNotNull { row -> row.getOrNull(j) }
        val wordForward = column.subList(i, (i + 4).coerceAtMost(column.size)).joinToString("")
        val wordBackward = column.subList((i - 3).coerceAtLeast(0), i + 1).joinToString("")
        return isGood(wordForward) + isGood(wordBackward)
    }

    fun getDiagonal(i: Int, j: Int, deltaI: Int, deltaJ: Int): String {
        val result = mutableListOf<Char>()
        for (step in 0..3) {
            val x = i + step * deltaI
            val y = j + step * deltaJ
            if (x in chars.indices && y in chars[x].indices) {
                result.add(chars[x][y])
            } else {
                break
            }
        }
        return result.joinToString("")
    }

    fun diagonal(i: Int, j: Int): Int {
        val wordDownForward = getDiagonal(i, j, 1, 1) // Diagonal Down-Forward
        val wordDownBackward = getDiagonal(i, j, 1, -1) // Diagonal Down-Backward
        val wordUpForward = getDiagonal(i, j, -1, 1) // Diagonal Up-Forward
        val wordUpBackward = getDiagonal(i, j, -1, -1) // Diagonal Up-Backward
        return isGood(wordDownForward) + isGood(wordDownBackward) +
                isGood(wordUpForward) + isGood(wordUpBackward)
    }

    fun found(i: Int, j: Int): Int =
        horizontal(chars[i], j) + vertical(i, j) + diagonal(i, j)

    fun part1(): Int {
        var acc = 0
        for (i in chars.indices) {
            for (j in chars[i].indices) {
                if (chars[i][j] == 'X') {
                    acc += found(i, j)
                }
            }
        }
        return acc
    }

    part1().println()
}
