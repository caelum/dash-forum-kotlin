package br.com.caelum.dashforumkotlin.data

import org.springframework.data.repository.Repository

interface CategoriaDao : Repository<Categoria, Long> {

    fun findAllByOrderByNome(): List<Categoria>

    fun save(categoria: Categoria)

    fun findByNome(nome: String): Categoria?
}