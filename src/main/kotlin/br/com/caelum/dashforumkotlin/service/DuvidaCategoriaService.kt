package br.com.caelum.dashforumkotlin.service

import br.com.caelum.dashforumkotlin.model.DuvidaCategoria
import br.com.caelum.dashforumkotlin.model.SubCategoria
import br.com.caelum.dashforumkotlin.model.Total
import br.com.caelum.dashforumkotlin.model.dto.DuvidaDTO
import br.com.caelum.dashforumkotlin.model.dto.ListaDeDuvidasDTO
import org.springframework.stereotype.Service

@Service
class DuvidaCategoriaService(private val buscador: BuscadorDeDuvidas) {


    fun listaDeDuvidasPorCategoria(): ArrayList<DuvidaCategoria> {

        val map = mapaDoServidor(buscador)

        return transformaEmLista(map)
    }


    fun totalDuvidasContaveis(): Total {

        val listaDeDuvidasPorCategoria = listaDeDuvidasPorCategoria()

        val semBusiness = listaDeDuvidasPorCategoria.filter(business)

        val contaveis = semBusiness.filter(offTopic)

        return Total(contaveis.sumBy { it.duvidas })
    }

    fun listaDaSubcategoria(chamada: String): ArrayList<SubCategoria> {

        val mapaDoServidor = mapaDoServidor(buscador)

        val mapa = criaMapaParaCategoria(chamada = chamada, mapaDoServidor = mapaDoServidor)

        return listaDeSubCategoriasDo(mapa)
    }


    companion object {

        private fun criaAtravesDas(duvidasDTO: ListaDeDuvidasDTO): Map<String, List<DuvidaDTO>> {

            val agrupadas = duvidasDTO.duvidas.groupBy { it.categoria }

            return agrupadas
        }


        private fun mapaDoServidor(buscador: BuscadorDeDuvidas): Map<String, List<DuvidaDTO>> {

            val duvidasDTO = buscador.buscaDuvidas()

            val map = criaAtravesDas(duvidasDTO)

            return map
        }

        private fun transformaEmLista(map: Map<String, List<DuvidaDTO>>): ArrayList<DuvidaCategoria> {

            val duvidasDaAlura = ArrayList<DuvidaCategoria>()

            map.forEach { categoria, duvidas -> duvidasDaAlura.add(DuvidaCategoria(categoria, duvidas)) }

            duvidasDaAlura.sortByDescending { it.categoria }

            return duvidasDaAlura
        }

        private fun criaMapaParaCategoria(mapaDoServidor: Map<String, List<DuvidaDTO>>, chamada: String): Map<String, List<DuvidaDTO>> {

            var chave = chamada

            if (chamada == "Off Topic") chave = ""

            val duvidas = mapaDoServidor[chave]

            return duvidas!!.groupBy { it.subCategoria }
        }


        private fun listaDeSubCategoriasDo(map: Map<String, List<DuvidaDTO>>): ArrayList<SubCategoria> {

            val subCategorias = ArrayList<SubCategoria>()

            map.forEach { subCategoria, lista -> subCategorias.add(SubCategoria(subCategoria, lista)) }

            subCategorias.sortBy { it.nome }

            return subCategorias
        }


        private val offTopic = { duvida: DuvidaCategoria -> duvida.categoria != "" }

        private val business = { duvida: DuvidaCategoria -> duvida.categoria != "Business" }


    }
}