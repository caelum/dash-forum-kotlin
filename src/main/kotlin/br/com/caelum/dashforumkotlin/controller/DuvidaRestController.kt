package br.com.caelum.dashforumkotlin.controller

import br.com.caelum.dashforumkotlin.service.DuvidaCategoriaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class DuvidaRestController(private val duvidaCategoriaService: DuvidaCategoriaService) {


    @GetMapping("/duvidas")
    fun duvidas(): ResponseEntity<Any> {

        return ResponseEntity.ok(duvidaCategoriaService.listaDeDuvidasPorCategoria())
    }

    @GetMapping("/duvidas/{nome}")
    fun subCategorias(@PathVariable("nome") nome: String): ResponseEntity<Any> {

        return ResponseEntity.ok(duvidaCategoriaService.listaDaSubcategoria(chamada = nome))

    }

    @GetMapping("/duvidas/qtd")
    fun qtdDuvidas(): ResponseEntity<Any> {

        return ResponseEntity.ok(duvidaCategoriaService.totalDuvidasContaveis())
    }


}