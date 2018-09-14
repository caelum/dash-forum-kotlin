package br.com.caelum.dashforumkotlin.task

import br.com.caelum.dashforumkotlin.data.Categoria
import br.com.caelum.dashforumkotlin.data.CategoriaDao
import br.com.caelum.dashforumkotlin.data.Historico
import br.com.caelum.dashforumkotlin.data.HistorioDao
import br.com.caelum.dashforumkotlin.model.DuvidaCategoria
import br.com.caelum.dashforumkotlin.service.DuvidaService
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
@EnableScheduling
class CategoriaTask(private val duvidaService: DuvidaService,
                    private val categoriaDao: CategoriaDao,
                    private val historioDao: HistorioDao) {


    @Scheduled(cron = "0 0 20 * * MON-FRI")
    fun salvaHistoricoDeDuvidasAs20H() {

        val listaDeDuvidasPorCategoria = duvidaService.listaDeDuvidasPorCategoria()

        listaDeDuvidasPorCategoria.forEach { duvida ->

            val categoria = pegaCategoriaGerenciadaAtravesDa(duvida)

            val hoje = Historico(duvida.quantidade)

            historioDao.save(hoje)

            categoria.historico.add(hoje)

            categoriaDao.save(categoria)

        }
    }

    private fun pegaCategoriaGerenciadaAtravesDa(duvida: DuvidaCategoria): Categoria {
        var categoria = categoriaDao.findByNome(duvida.categoria)

        if (categoria == null) {
            val nova = Categoria(duvida.categoria)
            categoriaDao.save(nova)
            categoria = nova
        }
        return categoria
    }
}