package br.com.victor.ifome.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.victor.ifome.R
import br.com.victor.ifome.model.Shop
import br.com.victor.ifome.sampledata.sampleShops
import br.com.victor.ifome.ui.theme.IfomeTheme
import coil.compose.AsyncImage

@Composable
fun Partner(
    shop: Shop,
    modifier: Modifier = Modifier,
) {
    Surface(modifier) {
        Column(Modifier
            .heightIn(150.dp, 200.dp)
            .width(100.dp)) {
            val imageSize = 100.dp
            AsyncImage(
                model = shop.logo,
                contentDescription = null,
                Modifier
                    .size(imageSize)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder)
            )

            Text(
                text = shop.name,
                fontSize = 16.sp,
                maxLines = 2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                overflow = TextOverflow.Ellipsis
            )

        }

    }

}

@Preview
@Composable
fun PartnerPreview() {
    IfomeTheme() {
        Surface {
            Partner(shop = sampleShops.random())
        }

    }
}