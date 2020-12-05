import java.io.File

class BinaryBoarding {

    private val input: File = File(javaClass.getResource("day-5-input.txt").file)

    private val ids = arrayListOf<Int>()

    fun solveFirstPart(): Int {
        var highest = 0

        this.input.forEachLine {
            val row = getRow(it.substring(0, 7))
            val column = getColumn(it.substring(7, 10))

            this.ids.add(row * 8 + column)

            if (row * 8 + column > highest) {
                highest = row * 8 + column
            }
        }

        return highest
    }

    fun solveSecondPart(): Int {
        this.input.forEachLine {
            val row = getRow(it.substring(0, 7))
            val column = getColumn(it.substring(7, 10))

            this.ids.add(row * 8 + column)
        }

        this.ids.sort()
        this.ids.reverse()

        for (i in 0..ids.size) {
            if (ids[i] - ids[i + 1] == 2) {
                return ids[i + 1] + 1
            }
        }

        throw IllegalStateException("Not found")
    }

    private fun getRow(input: String): Int {
        var currentTop = 127
        var currentBot = 0
        var diff = 128

        input.forEach {
            diff /= 2

            if (it == 'F') {
                currentTop -= diff
            }

            if (it == 'B') {
                currentBot += diff
            }
        }

        if (currentTop == currentBot) {
            return currentTop
        } else {
            throw IllegalStateException("Something went wrong")
        }
    }

    private fun getColumn(input: String): Int {
        var currentRight = 7
        var currentLeft = 0
        var diff = 8

        input.forEach {
            diff /= 2

            if (it == 'R') {
                currentLeft += diff
            }

            if (it == 'L') {
                currentRight -= diff
            }
        }

        if (currentLeft == currentRight) {
            return currentLeft
        } else {
            throw    IllegalStateException("Something went wrong")
        }
    }
}