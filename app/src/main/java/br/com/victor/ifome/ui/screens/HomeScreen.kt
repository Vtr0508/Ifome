package br.com.victor.ifome.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.victor.ifome.model.Product
import br.com.victor.ifome.sampledata.sampleProducts
import br.com.victor.ifome.sampledata.sampleSections
import br.com.victor.ifome.ui.components.CardProductItem
import br.com.victor.ifome.ui.components.ProductSection
import br.com.victor.ifome.ui.theme.IfomeTheme


@Composable
fun HomeScreen(sections: Map<String, List<Product>>, searchText: String = "") {
    Column {
        var text by remember() { mutableStateOf(searchText) }

        val searchedProducts = remember(text) {

            sampleProducts.filter { product ->
                product.name.contains(
                    text,
                    ignoreCase = true
                ) ||
                        product.description?.contains(
                            text,
                            ignoreCase = true
                        ) ?: false

            }


        }




        OutlinedTextField(
            value = text,
            onValueChange = { newValue ->
                text = newValue

            },
            Modifier
                .fillMaxWidth()
                .padding(
                    16.dp

                ),
            shape = RoundedCornerShape(100),
            label = {
                Text(text = "Produto")
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    null
                )
            },
            placeholder = {
                Text(text = "O que você procura?")
            }
        )
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            if (text.isBlank()) {
                for (section in sections) {
                    val title = section.key
                    val product = section.value
                    item {
                        ProductSection(
                            title = title,
                            produtos = product
                        )
                    }
                }
            } else {
                items(searchedProducts) { p ->
                    CardProductItem(
                        product = p,
                        Modifier.padding(horizontal = 16.dp)
                    )
                }

            }


        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    IfomeTheme() {
        Surface() {
            HomeScreen(sampleSections)
        }

    }


}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreviewSearchText() {
    IfomeTheme() {
        Surface() {
            HomeScreen(sampleSections, searchText = "pizza")
        }

    }


}