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

    fun transformaEmLista(map: Map<String, List<DuvidaDTO>>): ArrayList<DuvidaCategoria> {
        val duvidasDaAlura = ArrayList<DuvidaCategoria>()

        map.forEach { categoria, duvidas -> duvidasDaAlura.add(DuvidaCategoria(categoria, duvidas)) }
        return duvidasDaAlura
    }


    companion object {

        fun criaAtravesDas(duvidasDTO: ListaDeDuvidasDTO): Map<String, List<DuvidaDTO>> {

            val agrupadas = duvidasDTO.duvidas.groupBy { it.categoria }

            return agrupadas
        }

    }
}