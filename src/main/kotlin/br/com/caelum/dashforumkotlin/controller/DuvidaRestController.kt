package br.com.caelum.dashforumkotlin.controller

import br.com.caelum.dashforumkotlin.service.DuvidaCategoriaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/duvidas")
class DuvidaRestController(private val duvidaCategoriaService: DuvidaCategoriaService) {


    @GetMapping()
    fun duvidas(): ResponseEntity<Any> {

        return ResponseEntity.ok(duvidaCategoriaService.listaDeDuvidasPorCategoria())
    }

    @GetMapping("/{nome}")
    fun subCategorias(@PathVariable("nome") nome: String): ResponseEntity<Any> {

        return ResponseEntity.ok(duvidaCategoriaService.listaDaSubcategoria(chamada = nome))

    }

    @GetMapping("/qtd")
    fun qtdDuvidas(): ResponseEntity<Any> {

        return ResponseEntity.ok(duvidaCategoriaService.totalDuvidasContaveis())
    }


    @GetMapping("/qtd/grafico")
    fun dadosGrafico(): ResponseEntity<Any> {

        return ResponseEntity.ok(duvidaCategoriaService.dadosGrafico())
    }

}