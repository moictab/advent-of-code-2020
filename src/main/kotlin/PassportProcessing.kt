import java.io.File
import java.util.regex.Pattern

class PassportProcessing {

    private val input: File = File(javaClass.getResource("day-4-input.txt").file)

    fun solveFirstPart(): Int {
        var count = 0
        val chunks = this.input.readText().split("\r\n\r\n")
        chunks.forEach { chunk ->
            if (chunk.replace("cid:", "").count { c: Char -> c == ':' } > 6) {
                count++
            }
        }

        return count
    }

    fun solveSecondPart(): Int {
        var count = 0
        val passports = this.input.readText().split("\r\n\r\n")
        passports.forEach { passport ->
            val normalizedPassport = passport.replace("\r\n", " ")

            if (containsOnlyOnce(normalizedPassport, "byr") &&
                containsOnlyOnce(normalizedPassport, "iyr") &&
                containsOnlyOnce(normalizedPassport, "eyr") &&
                containsOnlyOnce(normalizedPassport, "hgt") &&
                containsOnlyOnce(normalizedPassport, "hcl") &&
                containsOnlyOnce(normalizedPassport, "ecl") &&
                containsOnlyOnce(normalizedPassport, "pid")
            ) {
                if (countValidFields(normalizedPassport.replace("\r\n", " ")) > 6) {
                    count++
                }
            }
        }

        return count
    }

    private fun countValidFields(input: String): Int {
        var validFields = 0
        val chunks = input.split(" ")
        val hairColorRegex = "^#([a-f0-9]{6})$"
        val eyeColorRegex = "amb|blu|brn|gry|grn|hzl|oth"
        val passportIdRegex = "^[0-9]{9}$"

        chunks.forEach { chunk ->
            val fieldType = chunk.split(":")[0]
            val fieldValue = chunk.split(":")[1]

            when (fieldType) {
                "byr" -> if (fieldValue.toInt() in 1920..2020) validFields++
                "iyr" -> if (fieldValue.toInt() in 2010..2020) validFields++
                "eyr" -> if (fieldValue.toInt() in 2020..2030) validFields++
                "hgt" -> {
                    val value = fieldValue.filter { it.isDigit() }.toInt()
                    if (fieldValue.endsWith("cm", false) && value in 150..193) {
                        validFields++
                    } else if (fieldValue.endsWith("in", false) && value in 59..76) {
                        validFields++
                    }
                }
                "hcl" -> if (Pattern.compile(hairColorRegex).matcher(fieldValue).matches()) validFields++
                "ecl" -> if (Pattern.compile(eyeColorRegex).matcher(fieldValue).matches()) validFields++
                "pid" -> if (Pattern.compile(passportIdRegex).matcher(fieldValue).matches()) validFields++
            }
        }

        return validFields
    }

    private fun containsOnlyOnce(text: String, fieldType: String): Boolean {
        val index = text.indexOf(fieldType)
        return index != -1 && index == text.lastIndexOf(fieldType)
    }
}