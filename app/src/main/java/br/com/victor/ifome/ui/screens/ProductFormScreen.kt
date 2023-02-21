package br.com.victor.ifome.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.victor.ifome.R
import br.com.victor.ifome.model.Product
import br.com.victor.ifome.ui.theme.IfomeTheme
import coil.compose.AsyncImage
import java.math.BigDecimal

class ProductFormUiState(
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val isShowPreview: Boolean = url.isNotBlank(),
    val onUrlChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {}

)

@Composable
fun ProductFormScreen(onSaveClick: (Product) -> Unit = {}) {

    var url by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var price by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    ProductFormScreen(state = ProductFormUiState(
        url = url,
        name = name,
        price = price,
        description = description,
        onUrlChange = {
            url = it
        }, onNameChange = {
            name = it
        }, onDescriptionChange = {
            description = it
        }
    ), onSaveClick = {
        val convertedPrice = try {
            BigDecimal(price)
        } catch (e: IllegalArgumentException){
            BigDecimal.ZERO
        }
        val product = Product(
            name = name,
            image = url,
            description = description,
            price = convertedPrice
        )
        onSaveClick(product)
    })

}

@Composable
fun ProductFormScreen(
    state: ProductFormUiState = ProductFormUiState(),
    onSaveClick: () -> Unit = {}
) {
    val url = state.url
    val name = state.name
    var price = state.price
    val description = state.description

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier)
        Text(
            text = "Cadastrar Produtos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        if (state.isShowPreview) {
            AsyncImage(
                model = url,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }

        TextField(
            value = url,
            onValueChange = state.onUrlChange,
            Modifier
                .fillMaxWidth(),
            label = {
                Text(text = "Url da imagem")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )


        TextField(
            value = name,
            onValueChange = state.onNameChange,
            Modifier
                .fillMaxWidth(),
            label = {
                Text(text = "Nome")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )

        var isPriceError by remember {
            mutableStateOf(false)
        }
        Column {
            TextField(
                value = price,
                onValueChange = {
                    isPriceError = try {
                        BigDecimal(it)
                        false
                    } catch (e: IllegalArgumentException) {
                        it.isNotEmpty()
                    }
                    price = it
                },
                Modifier
                    .fillMaxWidth(),
                isError = isPriceError,
                label = {
                    Text(text = "Preço")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
            )

            if (isPriceError) {
                Text(
                    text = "Preço inválido",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        TextField(value = description, onValueChange = state.onDescriptionChange, Modifier
            .fillMaxWidth()
            .heightIn(100.dp),
            label = {
                Text(text = "Descrição")
            })

        Button(
            onClick = onSaveClick,
            Modifier.fillMaxWidth()
        ) {
            Text(text = "Salvar")
        }
        Spacer(modifier = Modifier)


    }
}

@Preview
@Composable
fun ProductFormScreenPreview() {
    IfomeTheme {
        Surface {
            ProductFormScreen(state = ProductFormUiState())
        }
    }
    
}
