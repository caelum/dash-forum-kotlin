package br.com.caelum.dashforumkotlin.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DuvidaDTO(@JsonProperty("category") val categoria: String,
                     @JsonProperty("subcategory") val subCategoria: String,
                     @JsonProperty("color") val cor: String)
