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
import br.com.victor.ifome.model.Shop
import br.com.victor.ifome.sampledata.sampleProducts
import br.com.victor.ifome.sampledata.sampleSections
import br.com.victor.ifome.sampledata.sampleShopSections
import br.com.victor.ifome.ui.components.*
import br.com.victor.ifome.ui.theme.IfomeTheme


@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    searchText: String = "",
    partnerSections: Map<String, List<Shop>>,
) {
    Column {
        var text by remember() { mutableStateOf(searchText) }

        SearchTextField(
            searchText = text,
            onSearchChange = {
                text = it
            }
        )

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

                for (partner in partnerSections){
                    val title = partner.key
                    val shop = partner.value
                    item {
                        PartnerSection(
                            title = title,
                            shop = shop
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
            HomeScreen(sections = sampleSections, partnerSections = sampleShopSections)
        }

    }


}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreviewSearchText() {
    IfomeTheme() {
        Surface() {
            HomeScreen(sampleSections, searchText = "pizza", sampleShopSections)
        }

    }


}