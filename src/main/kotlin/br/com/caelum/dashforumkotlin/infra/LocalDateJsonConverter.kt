import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern


@Component
class LocalDateJsonConverter {

    class LocalDateSerializer : JsonSerializer<LocalDate>() {
        override fun serialize(data: LocalDate?,
                               json: JsonGenerator?,
                               provider: SerializerProvider?) {

            json?.writeString(FORMATTER.format(data))


        }


        companion object {

            private val FORMATTER = ofPattern("dd/MM/yyyy")
        }
    }
}