import java.io.File
import java.io.FileWriter

class TobogganTrajectory {

    private val input: File = File(javaClass.getResource("day-3-input.txt").file)

    fun solveFirstPart(): Int {
        val map = arrayListOf<String>()
        this.input.forEachLine { line ->
            map.add(line)
        }

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
}