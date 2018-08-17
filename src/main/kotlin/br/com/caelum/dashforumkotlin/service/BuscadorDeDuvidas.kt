package br.com.caelum.dashforumkotlin.service

import br.com.caelum.dashforumkotlin.model.dto.ListaDeDuvidasDTO
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class BuscadorDeDuvidas {

    private val URL = "https://cursos.alura.com.br/d23hd728h7h8f70fh0f0837fh74387fh3478/forum/sem-respostas"
    private val LIMPA_CACHE = "https://cursos.alura.com.br/d23hd728h7h8f70fh0f0837fh74387fh3478/forum/clean-cache"

    fun buscaDuvidas(): ListaDeDuvidasDTO {

        val template = RestTemplate()
        template.getForObject(LIMPA_CACHE, Void::class.java)
        return template.getForObject(URL, ListaDeDuvidasDTO::class.java)!!
    }


}
