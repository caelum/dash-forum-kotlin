package br.com.caelum.dashforumkotlin.model

enum class DuvidaStatus {
    SUSSA, TRANQUILO, SE_LIGA, DEU_RUIM;


    companion object {
        fun getInstance(tamanho: Int): DuvidaStatus = when (tamanho) {
            in 0..5 -> DuvidaStatus.SUSSA
            in 6..10 -> DuvidaStatus.TRANQUILO
            in 11..20 -> DuvidaStatus.SE_LIGA
            else -> DuvidaStatus.DEU_RUIM
        }
    }

}