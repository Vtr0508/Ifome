package br.com.victor.ifome.ui.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.victor.ifome.dao.ProductDao
import br.com.victor.ifome.model.Product
import br.com.victor.ifome.sampledata.sampleCandies
import br.com.victor.ifome.sampledata.sampleDrinks
import br.com.victor.ifome.sampledata.sampleProducts
import br.com.victor.ifome.sampledata.sampleShops
import br.com.victor.ifome.ui.states.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeScreenViewModel : ViewModel() {

    val dao = ProductDao()

    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(
        HomeScreenUiState()
    )
    val uiState get() = _uiState.asStateFlow()


    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchChange = {
                    _uiState.value = _uiState.value.copy(
                        searchText = it,
                        searchedProducts = searchedProducts(it)
                    )
                }
            )
        }
        viewModelScope.launch {
            dao.products().collect { products ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "Todos produtos" to products,
                        "Promoções" to sampleDrinks + sampleCandies,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks
                    ),
                    searchedProducts = searchedProducts(_uiState.value.searchText)
                )

            }
        }
    }


    private fun containsNameOrDescription(text: String) = { product: Product ->
        product.name.contains(
            text,
            false
        ) ||
                product.description?.contains(
                    text,
                    ignoreCase = true
                ) ?: false

    }

    private fun searchedProducts(text: String) = if (text.isNotBlank()) {
        sampleProducts.filter(containsNameOrDescription(text)) +
                dao.products().value.filter(containsNameOrDescription(text))
    } else emptyList()


}

