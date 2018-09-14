package br.com.caelum.dashforumkotlin.controller

import br.com.caelum.dashforumkotlin.model.DuvidaCategoria
import br.com.caelum.dashforumkotlin.model.SubCategoria
import br.com.caelum.dashforumkotlin.model.Total
import br.com.caelum.dashforumkotlin.service.DuvidaService
import br.com.caelum.dashforumkotlin.service.TotalService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/duvida")
class DuvidaRestController(private val duvidaService: DuvidaService,
                           private val totalService: TotalService) {

    @GetMapping()
    fun duvidas(): ResponseEntity<ArrayList<DuvidaCategoria>> {

        return ResponseEntity.ok(duvidaService.listaDeDuvidasPorCategoria())
    }

    @GetMapping("/{nome}")
    fun subCategorias(@PathVariable("nome") nome: String): ResponseEntity<ArrayList<SubCategoria>> {

        return ResponseEntity.ok(duvidaService.listaDaSubcategoria(chamada = nome))

    }

    @GetMapping("/qtd")
    fun qtdDuvidas(): ResponseEntity<Total> {

        return ResponseEntity.ok(totalService.totalDuvidasContaveis())
    }


}