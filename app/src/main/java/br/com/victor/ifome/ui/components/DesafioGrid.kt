package br.com.victor.ifome.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.victor.ifome.model.Product
import br.com.victor.ifome.sampledata.sampleProducts
import br.com.victor.ifome.ui.theme.IfomeTheme

@Composable
fun TelaGrid(products: List<Product>) {
    IfomeTheme() {
        Surface() {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item(span = {
                    GridItemSpan(maxLineSpan)
                }){
                    Text(text = "Todos Produtos", fontSize = 32.sp)
                }




                
                items(products) { p ->
                    ProdutoItem(produto = p)

                }
            }

        }

    }


}

@Preview
@Composable
fun TelaGridPreview() {
    TelaGrid(products = sampleProducts)
    
}