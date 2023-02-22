package br.com.victor.ifome.ui.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.victor.ifome.model.Product
import br.com.victor.ifome.model.Shop

data class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val partnerSections: Map<String, List<Shop>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val onSearchChange: (String) -> Unit = {},
    val searchText: String = ""
) {

    var text by mutableStateOf(searchText)


    fun isShowSections(): Boolean {
        return text.isBlank()
    }


}