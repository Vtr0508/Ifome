package br.com.victor.ifome.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.victor.ifome.ui.theme.IfomeTheme

@Composable
fun SearchTextField(
    searchText: String,
    onSearchChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = { newValue ->
            onSearchChange(newValue)

        },
        Modifier
            .fillMaxWidth()
            .padding(
                16.dp

            ),
        shape = RoundedCornerShape(100),
        label = {
            Text(text = "Produto")
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                null
            )
        },
        placeholder = {
            Text(text = "O que você procura?")
        }
    )
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    IfomeTheme() {
        Surface() {
            SearchTextField(searchText = "", onSearchChange = {})

        }

    }

}