
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class ApplicationTest {



    fun testCalculateDueDate() {
        val lastPeriodDate = LocalDate.of(2024, 1, 1)
        val cycleLength = 28
        val expectedDueDate = LocalDate.of(2024, 10, 8)
        assertEquals(expectedDueDate, calculateDueDate(lastPeriodDate, cycleLength))
    }

    private fun calculateDueDate(lastPeriodDate: LocalDate?, cycleLength: Int): LocalDate? {
        TODO("Not yet implemented")
    }


    fun testCalculateDueDateWithDifferentCycleLength() {
        val lastPeriodDate = LocalDate.of(2024, 3, 15)
        val cycleLength = 30
        val expectedDueDate = LocalDate.of(2024, 12, 22)
        assertEquals(expectedDueDate, calculateDueDate(lastPeriodDate, cycleLength))
    }
}
