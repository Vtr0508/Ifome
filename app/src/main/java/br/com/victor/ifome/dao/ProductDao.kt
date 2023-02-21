package br.com.victor.ifome.dao

import androidx.compose.runtime.mutableStateListOf
import br.com.victor.ifome.model.Product
import br.com.victor.ifome.sampledata.sampleProducts

class ProductDao {
    companion object {
        private val products = mutableStateListOf<Product>()
    }
    fun products() = products.toList()

    fun save(product: Product) {
        products.add(product)
    }
}