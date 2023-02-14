package br.com.victor.ifome.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.victor.ifome.R
import br.com.victor.ifome.model.Produto
import br.com.victor.ifome.sampledata.sampleDataProducts
import java.math.BigDecimal

@Composable
fun ProductSection(title: String, produtos: List<Produto>) {
    Column() {
        Text(
            text = title,
            Modifier.padding(
                start = 16.dp,
                end = 16.dp
            )
        )
        Row(
            Modifier
                .padding(bottom = 16.dp, top = 8.dp)
                .fillMaxWidth()
                .horizontalScroll(state = rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier)
            for (p in produtos) {
                ProdutoItem(produto = p)
            }
            Spacer(Modifier)
        }


    }

}

@Preview(showBackground = true)
@Composable
private fun ProductSecionPreview() {
    ProductSection(
        "Promoções", sampleDataProducts
    )
}

