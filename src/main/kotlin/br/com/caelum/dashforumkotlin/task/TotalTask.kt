package br.com.caelum.dashforumkotlin.task

import br.com.caelum.dashforumkotlin.data.TotalPorData
import br.com.caelum.dashforumkotlin.data.TotalPorDataDao
import br.com.caelum.dashforumkotlin.model.Total
import br.com.caelum.dashforumkotlin.service.TotalService
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
@EnableScheduling
class TotalTask(private val totalService: TotalService,
                private val dao: TotalPorDataDao) {

    @Scheduled(cron = "0 0 20 * * MON-FRI")
    fun buscaTotalProGrafico() {

        val total: Total = totalService.totalDuvidasContaveis()

        val totalPorData = TotalPorData(total)

        dao.save(totalPorData)
    }


}