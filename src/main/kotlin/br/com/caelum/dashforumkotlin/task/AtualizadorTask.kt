package br.com.caelum.dashforumkotlin.task

import br.com.caelum.dashforumkotlin.data.TotalPorData
import br.com.caelum.dashforumkotlin.data.TotalPorDataDao
import br.com.caelum.dashforumkotlin.model.Total
import br.com.caelum.dashforumkotlin.service.DuvidaCategoriaService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class AtualizadorTask(private val duvidaCategoriaService: DuvidaCategoriaService,
                      private val dao: TotalPorDataDao) {


    @Scheduled(cron = "0 0 20 * * MON-FRI")
    fun buscaDadosPraGrafico() {
        
        val total: Total = duvidaCategoriaService.totalDuvidasContaveis()

        val totalPorData = TotalPorData(total)

        dao.save(totalPorData)
    }
}