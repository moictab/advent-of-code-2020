import java.io.File

fun main() {
    println("Day 1, first part: " + ReportRepair().solveFirstPart())
    println("Day 2, second part: " + ReportRepair().solveSecondPart())
}

class ReportRepair {
    private val input: File = File(javaClass.getResource("input.txt").file)
    private val numbers = arrayListOf<Int>()

    fun solveFirstPart(): Int {
        this.input.forEachLine { this.numbers.add(it.toInt()) }
        this.numbers.forEachIndexed { index, number ->
            run {
                for (i in index until this.numbers.size) {
                    if (number + numbers[i] == 2020) {
                        return number * numbers[i]
                    }
                }
            }
        }

        throw IllegalArgumentException("Invalid input")
    }

    fun solveSecondPart(): Int {
        this.input.forEachLine { this.numbers.add(it.toInt()) }
        for (i in 0 until numbers.size) {
            for (j in i until numbers.size) {
                for (k in j until numbers.size) {
                    if (numbers[i] + numbers[j] + numbers[k] == 2020) {
                        return numbers[i] * numbers[j] * numbers[k]
                    }
                }
            }
        }

        throw IllegalArgumentException("Invalid input")
    }
}