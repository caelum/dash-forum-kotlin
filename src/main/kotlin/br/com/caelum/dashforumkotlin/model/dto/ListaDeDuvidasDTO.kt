package br.com.caelum.dashforumkotlin.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ListaDeDuvidasDTO(@JsonProperty("list") val duvidas: List<DuvidaDTO>)