package br.com.caelum.dashforumkotlin.data

import LocalDateJsonConverter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Historico() {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    var id: Long = 0

    @JsonSerialize(using = LocalDateJsonConverter.LocalDateSerializer::class)
    lateinit var data: LocalDate

    var qtd: Int = 0


    constructor(quantidade: Int) : this() {
        this.qtd = quantidade
        this.data = LocalDate.now()
    }

}
