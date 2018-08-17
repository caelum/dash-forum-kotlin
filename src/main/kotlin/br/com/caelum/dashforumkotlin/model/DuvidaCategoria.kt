package br.com.caelum.dashforumkotlin.model

import br.com.caelum.dashforumkotlin.model.dto.DuvidaDTO

class DuvidaCategoria(val categoria: String, duvidas: List<DuvidaDTO>) {

    val status: DuvidaStatus = statusDas(duvidas)
    val duvidas = duvidas.count()
    val cor: String= duvidas[0].cor


    private fun statusDas(duvidas: List<DuvidaDTO>): DuvidaStatus =

            when (duvidas.size) {
                in 0..5 -> DuvidaStatus.SUSSA
                in 6..10 -> DuvidaStatus.TRANQUILO
                in 11..15 -> DuvidaStatus.SE_LIGA
                else -> DuvidaStatus.DEU_RUIM
            }
}