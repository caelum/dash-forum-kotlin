package br.com.caelum.dashforumkotlin.data

import org.springframework.data.repository.Repository

interface HistorioDao : Repository<Historico, Long> {

    fun save(historico: Historico)
}