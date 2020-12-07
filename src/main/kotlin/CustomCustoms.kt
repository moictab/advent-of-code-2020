import java.io.File

class CustomCustoms {

    private val input: File = File(javaClass.getResource("day-6-input.txt").file)

    fun solveFirstPart(): Int {
        var count = 0
        val chunks = this.input.readText().split("\r\n\r\n")
        chunks.forEach { chunk ->
            count += chunk.replace("\r\n", "").toCharArray().distinct().size
        }

        return count
    }

    fun solveSecondPart(): Int {
        var count = 0
        val chunks = this.input.readText().split("\r\n\r\n")
        chunks.forEach { chunk ->
            val lines = chunk.split("\r\n")
            lines.first().toCharArray().forEach { if (isContained(it, lines)) count++ }
        }

        return count
    }

    private fun isContained(answer: Char, lines: List<String>): Boolean {
        lines.forEach { line -> if (answer !in line) return false }
        return true
    }

}