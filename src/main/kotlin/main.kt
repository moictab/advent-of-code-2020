import java.io.File

fun main() {
    println("Day 1: " + ReportRepair().solve())
}

class ReportRepair {
    private val input: File = File(javaClass.getResource("input.txt").file)
    private val numbers = arrayListOf<Int>()

    fun solve(): Int {
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
}