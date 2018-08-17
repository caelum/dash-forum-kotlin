package br.com.caelum.dashforumkotlin.service

import br.com.caelum.dashforumkotlin.model.DuvidaCategoria
import br.com.caelum.dashforumkotlin.model.dto.DuvidaDTO
import br.com.caelum.dashforumkotlin.model.dto.ListaDeDuvidasDTO
import org.springframework.stereotype.Service

@Service
class DuvidaCategoriaService(private val buscador: BuscadorDeDuvidas) {


    fun listaDeDuvidasPorCategoria(): ArrayList<DuvidaCategoria> {

        val duvidasDTO = buscador.buscaDuvidas()

        val map = criaAtravesDas(duvidasDTO)

        return transformaEmLista(map)
    }

    private fun transformaEmLista(map: Map<String, List<DuvidaDTO>>): ArrayList<DuvidaCategoria> {
        val duvidasDaAlura = ArrayList<DuvidaCategoria>()

        map.forEach { categoria, duvidas -> duvidasDaAlura.add(DuvidaCategoria(categoria, duvidas)) }
        return duvidasDaAlura
    }


    fun totalDuvidasContaveis(): Int {

        val listaDeDuvidasPorCategoria = listaDeDuvidasPorCategoria()
        val semBusiness = listaDeDuvidasPorCategoria.filter(business)
        val contaveis = semBusiness.filter(offTopic)
        return contaveis.sumBy { it.duvidas }
    }

    companion object {

        fun criaAtravesDas(duvidasDTO: ListaDeDuvidasDTO): Map<String, List<DuvidaDTO>> {

            val agrupadas = duvidasDTO.duvidas.groupBy { it.categoria }

            return agrupadas
        }

        private val offTopic = { duvida: DuvidaCategoria -> duvida.categoria != "" }

        private val business = { duvida: DuvidaCategoria -> duvida.categoria != "Business" }


    }
}