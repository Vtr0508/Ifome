package br.com.victor.ifome.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.victor.ifome.R
import br.com.victor.ifome.extensions.toBrazillianCurrency
import br.com.victor.ifome.model.Product
import br.com.victor.ifome.ui.theme.IfomeTheme
import coil.compose.AsyncImage
import java.math.BigDecimal

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    expanded: Boolean = false,

    ) {
   var expandedState by remember {
       mutableStateOf(expanded)
   }

    Card(
        modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .clickable {
                expandedState = !expandedState
            },
        elevation = elevation
    ) {
        Column() {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
                    .padding(16.dp)
            ) {
                Text(text = product.name)
                Text(text = product.price.toBrazillianCurrency())


            }

            val textOverFlow =
                if (expandedState) TextOverflow.Visible
                else TextOverflow.Ellipsis

            val maxLines =
                if (expandedState) Int.MAX_VALUE
                else 2


            product.description?.let {
                Text(
                    text = product.description,
                    Modifier
                        .padding(16.dp),
                    overflow = textOverFlow,
                    maxLines = maxLines
                )
            }


        }


    }


}

@Preview
@Composable
fun CardProductItemPreview() {
    IfomeTheme() {
        Surface() {
            CardProductItem(
                product = Product(
                    name = "Teste",
                    price = BigDecimal("4.99"),
                    description = LoremIpsum(50).values.first()
                ), expanded = false)

        }

    }

}