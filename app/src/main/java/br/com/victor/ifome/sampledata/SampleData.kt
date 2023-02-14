package br.com.victor.ifome.sampledata

import br.com.victor.ifome.R
import br.com.victor.ifome.model.Produto
import java.math.BigDecimal

val sampleDataProducts = listOf(
    Produto(
        nome = "Hamburguer",
        valor = BigDecimal("19.99"),
        imagem = R.drawable.burger
    ), Produto(
        nome = "Pizza",
        valor = BigDecimal("29.99"),
        imagem = R.drawable.pizza
    ), Produto(
        nome = "Fritas",
        valor = BigDecimal("9.99"),
        imagem = R.drawable.fries
    )

)