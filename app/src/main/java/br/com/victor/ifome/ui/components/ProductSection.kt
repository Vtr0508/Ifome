package br.com.victor.ifome.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.victor.ifome.model.Product
import br.com.victor.ifome.sampledata.sampleProducts

import br.com.victor.ifome.ui.theme.IfomeTheme

@Composable
fun ProductSection(
    modifier: Modifier = Modifier,
    title: String,
    produtos: List<Product>,
) {
    Column() {
        Text(
            text = title,
            modifier.padding(
                start = 16.dp,
                end = 16.dp
            )
        )
        LazyRow(
            Modifier
                .padding(bottom = 16.dp, top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(produtos) { p ->
                ProdutoItem(produto = p)

            }


        }


    }

}

@Preview(showBackground = true)
@Composable
private fun ProductSecionPreview() {
    IfomeTheme() {
        Surface() {
            ProductSection(
               title = "Promoções", produtos = sampleProducts
            )
        }

    }

}

