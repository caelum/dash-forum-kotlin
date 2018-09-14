package br.com.caelum.dashforumkotlin.model

import br.com.caelum.dashforumkotlin.model.dto.DuvidaDTO

class DuvidaCategoria(val categoria: String, duvidas: List<DuvidaDTO>) {

    val status: DuvidaStatus = DuvidaStatus.getInstance(duvidas.size)
    val quantidade = duvidas.count()
    val cor: String = duvidas[0].cor
}