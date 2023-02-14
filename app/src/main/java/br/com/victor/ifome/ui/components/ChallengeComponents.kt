package br.com.victor.ifome.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.victor.ifome.R.drawable.ic_launcher_background
import br.com.victor.ifome.ui.theme.Purple200
import br.com.victor.ifome.ui.theme.Purple500
import br.com.victor.ifome.ui.theme.Purple700
import br.com.victor.ifome.ui.theme.Teal200


@Composable
fun ChallengeCompose(descricao: String = "") {
    Surface(shape = RoundedCornerShape(15.dp), elevation = 4.dp) {
        Column(
            Modifier
                .heightIn(250.dp,260.dp)
                .width(200.dp)
                .verticalScroll(rememberScrollState())
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
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = ic_launcher_background),
                    contentDescription = null,
                    Modifier
                        .offset(y = imageSize / 2)
                        .size(imageSize)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter)
                )
            }
            Column(Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(imageSize / 2))

                Text(
                    text = LoremIpsum(50).values.first(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700)
                )
                Text(
                    text = "R$ 14.99",
                    Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),

                    )


            }
            if (descricao.isNotBlank()) {
                Text(
                    text = descricao,
                    Modifier
                        .background(MaterialTheme.colors.primary)
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp,
                            top = 8.dp
                        ), color = MaterialTheme.colors.onPrimary

                )
            }


        }

    }


}

@Preview(showBackground = true)
@Composable
fun ChallengeComposePreview() {
    ChallengeCompose(LoremIpsum(50).values.first())
}