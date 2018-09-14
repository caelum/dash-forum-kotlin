package br.com.caelum.dashforumkotlin.data

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Categoria() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    var id: Long = 0

    lateinit var nome: String

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "categoria_id")
    val historico: MutableList<Historico> = ArrayList()


    constructor(nome: String) : this() {
        this.nome = nome
    }


}