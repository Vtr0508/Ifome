package br.com.victor.ifome.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.victor.ifome.model.Shop
import br.com.victor.ifome.sampledata.sampleShops
import br.com.victor.ifome.ui.theme.IfomeTheme

@Composable
fun PartnerSection(
    title: String,
    shop: List<Shop>,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(text = title, modifier
            .padding(
                start = 16.dp,
                end = 16.dp
            ), fontSize = 28.sp,
            fontWeight = FontWeight(400),
            overflow = TextOverflow.Ellipsis)
        
        LazyRow(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ){

            items(shop){s ->
                Partner(shop = s)

            }

        }
    }

}

@Preview
@Composable
fun PartnerSectionPreview() {
    IfomeTheme {
        Surface {
            PartnerSection(title = "Lojas", shop = sampleShops)
        }
    }

}