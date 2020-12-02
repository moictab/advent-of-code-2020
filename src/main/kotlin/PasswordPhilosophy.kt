import java.io.File

class PasswordPhilosophy {

    private val input: File = File(javaClass.getResource("day-2-input.txt").file)

    fun solve(): Int {
        var count = 0

        this.input.forEachLine { line ->
            val chunks = line.split(" ")
            val range = this.getRange(chunks[0])
            val charToFind = chunks[1].replace(":", "")
            if (chunks[2].count { it.toString() == charToFind } in range) {
                count++
            }
        }

        return count
    }

    private fun getRange(input: String): IntRange {
        val chunks = input.split("-")
        repeat(chunks.size) {
            return IntRange(chunks.first().toInt(), chunks.last().toInt())
        }

        throw IllegalArgumentException("Invalid input")
    }
}