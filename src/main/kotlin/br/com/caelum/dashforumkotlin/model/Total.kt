package br.com.caelum.dashforumkotlin.model

class Total(val qtd: Int) {
    val status: DuvidaStatus = DuvidaStatus.getInstance(qtd)
}