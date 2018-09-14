package br.com.caelum.dashforumkotlin.service

import br.com.caelum.dashforumkotlin.data.Categoria
import br.com.caelum.dashforumkotlin.data.CategoriaDao
import br.com.caelum.dashforumkotlin.model.DuvidaCategoria
import br.com.caelum.dashforumkotlin.model.SubCategoria
import br.com.caelum.dashforumkotlin.model.dto.DuvidaDTO
import br.com.caelum.dashforumkotlin.model.dto.ListaDeDuvidasDTO
import org.springframework.stereotype.Service

@Service
class DuvidaService(private val buscador: BuscadorDeDuvidas,
                    private val dao: CategoriaDao) {


    fun listaDeDuvidasPorCategoria(): ArrayList<DuvidaCategoria> {

        val map = mapaDoServidor(buscador)

        return transformaEmLista(map)
    }


    fun listaDaSubcategoria(chamada: String): ArrayList<SubCategoria> {

        val mapaDoServidor = mapaDoServidor(buscador)

        val mapa = criaMapaParaCategoria(chamada = chamada, mapaDoServidor = mapaDoServidor)

        return listaDeSubCategoriasDo(mapa)
    }


    fun totalDeDuvidasPorCategoria(): List<Categoria> {

        return dao.findAllByOrderByNome()
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


    }
}