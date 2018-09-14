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

        val semBusiness = listaDeDuvidasPorCategoria.filter(business)

        val contaveis = semBusiness.filter(offTopic)

        return Total(contaveis.sumBy { it.quantidade })
    }


    private val offTopic = { duvida: DuvidaCategoria -> duvida.categoria != "" }

    private val business = { duvida: DuvidaCategoria -> duvida.categoria != "Business" }

}