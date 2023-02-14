package br.com.victor.ifome.model

import androidx.annotation.DrawableRes
import java.math.BigDecimal

class Produto(
    val nome: String,
    val valor: BigDecimal,
    @DrawableRes val imagem: Int
) {

}
