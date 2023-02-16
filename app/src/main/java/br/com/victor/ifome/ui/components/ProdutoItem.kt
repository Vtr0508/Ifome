package br.com.victor.ifome.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.victor.ifome.R
import br.com.victor.ifome.extensions.toBrazillianCurrency
import br.com.victor.ifome.model.Product
import br.com.victor.ifome.ui.theme.IfomeTheme
import coil.compose.AsyncImage
import java.math.BigDecimal

@Composable
fun ProdutoItem(produto: Product, modifier: Modifier = Modifier) {
    Surface(shape = RoundedCornerShape(15.dp), elevation = 4.dp) {
        Column(
            modifier
                .width(200.dp)
                .heightIn(250.dp, 300.dp)
        ) {
            val imageSize = 100.dp
            Box(
                modifier = Modifier
                    .height(imageSize)
                    .background(Color.Blue)
                    .width(200.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.secondary
                            )
                        )
                    )
            ) {
                AsyncImage(
                    produto.image,
                    contentDescription = null,
                    Modifier
                        .offset(y = imageSize / 2)
                        .size(imageSize)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder)
                )
            }
            Column(Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(imageSize / 2))

                Text(
                    text = produto.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700)
                )
                Text(
                    text = produto.price.toBrazillianCurrency(),
                    Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),

                    )


            }

        }

    }

}

@Preview(showBackground = true)
@Composable
private fun ProdutoItemPreview() {
    IfomeTheme() {
        Surface {
            ProdutoItem(
                Product(
                    name = LoremIpsum(50).values.first(),
                    price = BigDecimal("14.99"),

                )
            )
        }
    }


}