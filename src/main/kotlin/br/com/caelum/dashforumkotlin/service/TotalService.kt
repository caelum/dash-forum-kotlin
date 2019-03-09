package br.com.caelum.dashforumkotlin.service

import br.com.caelum.dashforumkotlin.data.TotalPorData
import br.com.caelum.dashforumkotlin.data.TotalPorDataDao
import br.com.caelum.dashforumkotlin.model.DuvidaCategoria
import br.com.caelum.dashforumkotlin.model.Total
import org.springframework.stereotype.Service

@Service
class TotalService(val dao: TotalPorDataDao,
                   val duvidaService: DuvidaService) {

    fun totalDeDuvidasAbertas(): List<TotalPorData> {

        return dao.findAll()
    }

    fun totalDuvidasContaveis(): Total {

        val listaDeDuvidasPorCategoria = duvidaService.listaDeDuvidasPorCategoria()

        return Total(listaDeDuvidasPorCategoria.sumBy { it.quantidade })
    }
}