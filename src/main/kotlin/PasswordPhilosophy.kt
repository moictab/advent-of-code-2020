import java.io.File

class PasswordPhilosophy {

    private val input: File = File(javaClass.getResource("day-2-input.txt").file)

    fun solveFirstPart(): Int {
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

    fun solveSecondPart(): Int {
        var count = 0

        this.input.forEachLine { line ->
            var found = 0
            val chunks = line.split(" ")
            val positions = this.getPositions(chunks[0])
            val charToFind = chunks[1].replace(":", "")

            positions.forEach {
                if (chunks[2].toCharArray()[it - 1] == charToFind.toCharArray()[0]) {
                    found++
                }
            }

            if (found == 1) {
                count++
            }
        }

        return count
    }

    private fun getRange(input: String): IntRange {
        val chunks = input.split("-")
        repeat(chunks.size) { return IntRange(chunks.first().toInt(), chunks.last().toInt()) }
        throw IllegalArgumentException("Invalid input")
    }

    private fun getPositions(input: String): IntArray {
        val positions = arrayListOf<Int>()
        val chunks = input.split("-")
        chunks.forEach { positions.add(it.toInt()) }
        return positions.toIntArray()
    }
}