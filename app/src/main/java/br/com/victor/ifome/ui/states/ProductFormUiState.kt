package br.com.victor.ifome.ui.states

data class ProductFormUiState(
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val isPriceError: Boolean = false,
    val isShowPreview: Boolean = url.isNotBlank(),
    val onUrlChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {}

)