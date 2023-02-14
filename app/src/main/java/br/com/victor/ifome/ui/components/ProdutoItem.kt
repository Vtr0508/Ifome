package br.com.victor.ifome.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import br.com.victor.ifome.model.Produto
import br.com.victor.ifome.ui.theme.Purple500
import br.com.victor.ifome.ui.theme.Teal200
import java.math.BigDecimal

@Composable
fun ProdutoItem(produto: Produto) {
    Surface(shape = RoundedCornerShape(15.dp), elevation = 4.dp) {
        Column(
            Modifier
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
                                Purple500, Teal200
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(produto.imagem),
                    contentDescription = null,
                    Modifier
                        .offset(y = imageSize / 2)
                        .size(imageSize)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.Crop
                )
            }
            Column(Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(imageSize / 2))

                Text(
                    text = produto.nome,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700)
                )
                Text(
                    text = produto.valor.toBrazillianCurrency(),
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
    ProdutoItem(
        Produto(
            nome = LoremIpsum(50).values.first(),
            valor = BigDecimal("14.99"),
            R.drawable.placeholder
        )
    )

}