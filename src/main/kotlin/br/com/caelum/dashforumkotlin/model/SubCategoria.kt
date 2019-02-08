package br.com.caelum.dashforumkotlin.model

import br.com.caelum.dashforumkotlin.model.dto.DuvidaDTO

class SubCategoria(val nome: String, private val lista: List<DuvidaDTO>) {

    val qtd = lista.count()
    val status: DuvidaStatus = DuvidaStatus.getInstance(qtd)
    val codigo = lista[0].codigoSubCategoria
}