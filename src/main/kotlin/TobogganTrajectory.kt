import java.io.File

class TobogganTrajectory {

    private val input: File = File(javaClass.getResource("day-3-input.txt").file)

    fun solveFirstPart(): Int {
        val map = arrayListOf<String>()
        this.input.forEachLine { line -> map.add(line) }

        var count = 0
        var rowIndex = 0
        var columnIndex = 0

        while (rowIndex < map.size - 1) {
            rowIndex++
            columnIndex += 3

            if (columnIndex >= map[rowIndex].length) {
                columnIndex -= map[rowIndex].length
            }

            if (map[rowIndex][columnIndex] == "#".toCharArray()[0]) {
                count++
            }
        }

        return count
    }

    fun solveSecondPart(): Long {
        var count = 1L

        val slopes = arrayOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
        for (slope in slopes) {
            val result = solve(slope)
            println("Slope: $slope, result: $result")
            count *= result
        }

        return count
    }

    private fun solve(slope: Pair<Int, Int>): Int {
        val map = arrayListOf<String>()
        this.input.forEachLine { line -> map.add(line) }

        var count = 0
        var rowIndex = 0
        var columnIndex = 0

        while (rowIndex < map.size - 1) {
            columnIndex += slope.first
            rowIndex += slope.second

            if (columnIndex >= map[rowIndex].length) {
                columnIndex -= map[rowIndex].length
            }

            if (map[rowIndex][columnIndex] == "#".toCharArray()[0]) {
                count++
            }
        }

        return count
    }
}