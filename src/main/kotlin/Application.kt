import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(DefaultHeaders)
        install(CallLogging)
        install(ContentNegotiation) {
            register(ContentType.Text.Html, kotlinx.html.HtmlContentConverter())
        }
        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK) {
                    head {
                        title { +"Geburtsterminrechner" }
                    }
                    body {
                        h1 { +"Geburtsterminrechner" }
                        form(action = "/calculate", method = FormMethod.post) {
                            p {
                                +"Datum des letzten Menstruationszyklus (dd.MM.yyyy):"
                                textInput(name = "lastPeriodDate")
                            }
                            p {
                                +"Durchschnittliche Zykluslänge:"
                                numberInput(name = "cycleLength")
                            }
                            p {
                                submitInput { value = "Berechnen" }
                            }
                        }
                    }
                }
            }
            post("/calculate") {
                val params = call.receiveParameters()
                val lastPeriodDateInput = params["lastPeriodDate"]
                val cycleLengthInput = params["cycleLength"]?.toIntOrNull() ?: 28 // Default auf 28 Tage

                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                val lastPeriodDate = LocalDate.parse(lastPeriodDateInput, formatter)
                val dueDate = calculateDueDate(lastPeriodDate, cycleLengthInput)

                call.respondHtml(HttpStatusCode.OK) {
                    head {
                        title { +"Geburtsterminrechner - Ergebnis" }
                    }
                    body {
                        h1 { +"Geburtsterminrechner - Ergebnis" }
                        p {
                            +"Der voraussichtliche Geburtstermin ist: ${dueDate.format(formatter)}"
                        }
                        a(href = "/") { +"Zurück" }
                    }
                }
            }
        }
    }.start(wait = true)
}

fun calculateDueDate(lastPeriodDate: LocalDate, cycleLength: Int): LocalDate {
    // Add 280 days to the date of the last menstrual period
    return lastPeriodDate.plusDays(280 - (28 - cycleLength))
}
