package br.com.caelum.dashforumkotlin.data

import LocalDateJsonConverter
import br.com.caelum.dashforumkotlin.model.DuvidaStatus
import br.com.caelum.dashforumkotlin.model.Total
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalDate
import javax.persistence.*

@Entity
class TotalPorData() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    var id: Long = 0

    @Enumerated(value = EnumType.STRING)
    lateinit var status: DuvidaStatus

    var qtd: Int = 0

    @JsonSerialize(using = LocalDateJsonConverter.LocalDateSerializer::class)
    lateinit var data: LocalDate

    constructor(total: Total) : this() {
        qtd = total.qtd
        status = total.status
        data = LocalDate.now()
    }


}