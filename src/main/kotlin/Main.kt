
import java.time.LocalDate
import java.time.format.DateTimeFormatter
class Main {
    fun main() {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        println("Geben Sie das Datum des letzten Menstruationszyklus ein (dd.MM.yyyy): ")
        val lastPeriodDateInput = readLine()
        val lastPeriodDate = LocalDate.parse(lastPeriodDateInput, formatter)

        println("Geben Sie die durchschnittliche Zykluslänge ein: ")
        val cycleLengthInput = readLine()
        val cycleLength = cycleLengthInput?.toIntOrNull() ?: 28 // Default auf 28 Tage

        val dueDate = calculateDueDate(lastPeriodDate, cycleLength)
        println("Der voraussichtliche Geburtstermin ist: ${dueDate.format(formatter)}")
    }

    fun calculateDueDate(lastPeriodDate: LocalDate, cycleLength: Int): LocalDate {
        // Add 280 days to the date of the last menstrual period
        return lastPeriodDate.plusDays((280 - (28 - cycleLength)).toLong())
    }

}