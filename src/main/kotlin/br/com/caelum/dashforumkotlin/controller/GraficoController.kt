package br.com.caelum.dashforumkotlin.controller

import br.com.caelum.dashforumkotlin.data.Categoria
import br.com.caelum.dashforumkotlin.data.TotalPorData
import br.com.caelum.dashforumkotlin.service.DuvidaService
import br.com.caelum.dashforumkotlin.service.TotalService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/graficos")
class GraficoController(val totalService: TotalService, val duvidaService: DuvidaService) {


    @GetMapping("/total")
    fun totalDeDuvidasAbertasProGrafico(): ResponseEntity<List<TotalPorData>> {

        return ResponseEntity.ok(totalService.totalDeDuvidasAbertas())
    }

    @GetMapping("/categorias")
    fun totalDeDuvidasPorCategoriaAbertasProGrafico(): ResponseEntity<List<Categoria>> {

        return ResponseEntity.ok(duvidaService.totalDeDuvidasPorCategoria())
    }
}