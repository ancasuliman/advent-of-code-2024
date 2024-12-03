import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(path: String): List<String> = Path(path).readText().trim().lines()

/**
 * Convert a line of the input to a list of integers .
 */
fun lineToIntegers(line: String): List<Int> = line.split("\\s+".toRegex()).map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
