package br.com.victor.ifome.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun BigDecimal.toBrazillianCurrency():String{
    return NumberFormat
        .getCurrencyInstance(Locale("pt","br"))
        .format(this)
}