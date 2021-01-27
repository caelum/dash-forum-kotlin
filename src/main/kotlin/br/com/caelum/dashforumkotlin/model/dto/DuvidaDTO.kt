package br.com.caelum.dashforumkotlin.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

class DuvidaDTO(@JsonProperty("category") val categoria: String,
                @JsonProperty("subcategory") val subCategoria: String,
                @JsonProperty("color") val cor: String,
                @JsonProperty("subcategoryCode") var codigoSubCategoria: String) {

    @JsonProperty("categoryCode") var codigoCategoria = ""
    set(value) {
        field = if(value.isEmpty())
            "offtopic"
        else
            value
    }
}
