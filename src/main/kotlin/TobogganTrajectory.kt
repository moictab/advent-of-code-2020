import java.io.File

class TobogganTrajectory {

    private val map = arrayListOf<String>()
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

            if (map[rowIndex][columnIndex] == '#') {
                count++
            }
        }

        return count
    }

    fun solveSecondPart(): Long {
        this.input.forEachLine { line -> map.add(line) }
        val slopes = arrayOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
        return slopes.fold(1L, { count, slope -> count * solve(slope) })
    }

    private fun solve(slope: Pair<Int, Int>): Int {
        var count = 0
        var rowIndex = 0
        var columnIndex = 0

        while (rowIndex < map.size - 1) {
            rowIndex += slope.second
            columnIndex = (columnIndex + slope.first) % map[rowIndex].length

            if (map[rowIndex][columnIndex] == '#') {
                count++
            }
        }

        return count
    }
}