package br.com.caelum.dashforumkotlin.data

import org.springframework.data.repository.Repository

interface TotalPorDataDao : Repository<TotalPorData, Long> {

    fun save(totalPorData: TotalPorData)

    fun findAll() : List<TotalPorData>
}