package br.com.victor.ifome.ui.viewmodels

import androidx.lifecycle.ViewModel
import br.com.victor.ifome.dao.ProductDao
import br.com.victor.ifome.model.Product
import br.com.victor.ifome.ui.states.ProductFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal

class ProductFormScreenViewModel : ViewModel() {


    val dao = ProductDao()

    private val _uiState: MutableStateFlow<ProductFormUiState> = MutableStateFlow(
        ProductFormUiState()
    )
    val uiState get() = _uiState.asStateFlow()




    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUrlChange = {
                    _uiState.value = _uiState.value.copy(
                        url = it,
                        isShowPreview = it.isNotBlank()
                    )
                },
                onNameChange = {
                    _uiState.value = _uiState.value.copy(
                        name = it
                    )
                },
                onDescriptionChange = {
                    _uiState.value = _uiState.value.copy(
                        description = it
                    )
                },
                onPriceChange = {
                   val isPriceError = try {
                        BigDecimal(it)
                        false
                    } catch (e: IllegalArgumentException) {
                        it.isNotEmpty()
                        true
                    }
                    _uiState.value = _uiState.value.copy(
                        price = it,
                        isPriceError = isPriceError
                    )

                }
            )

        }
    }

    fun save() {
        _uiState.value.run {
            val converted = try {
                BigDecimal(price)
            }catch (e: java.lang.NumberFormatException){
                BigDecimal.ZERO
            }
            val product = Product(
                name = name,
                price = converted,
                image = url,
                description = description
            )
            dao.save(product)
        }

    }
}