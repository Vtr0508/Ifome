package br.com.victor.ifome.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import br.com.victor.ifome.ui.states.ProductFormUiState
import br.com.victor.ifome.ui.theme.IfomeTheme
import br.com.victor.ifome.ui.viewmodels.ProductFormScreenViewModel
import coil.compose.AsyncImage


@Composable
fun ProductFormScreen(
    viewModel: ProductFormScreenViewModel,
    onSaveClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    ProductFormScreen(state = state, onSaveClick = {
        viewModel.save()
        onSaveClick()
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
    val isPriceError = state.isPriceError

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


        Column {
            TextField(
                value = price,
                onValueChange = state.onPriceChange,
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
