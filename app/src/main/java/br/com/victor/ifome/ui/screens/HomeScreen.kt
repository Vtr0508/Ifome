package br.com.victor.ifome.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.victor.ifome.sampledata.*
import br.com.victor.ifome.ui.components.CardProductItem
import br.com.victor.ifome.ui.components.PartnerSection
import br.com.victor.ifome.ui.components.ProductSection
import br.com.victor.ifome.ui.components.SearchTextField
import br.com.victor.ifome.ui.states.HomeScreenUiState
import br.com.victor.ifome.ui.theme.IfomeTheme
import br.com.victor.ifome.ui.viewmodels.HomeScreenViewModel



@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel
) {

    val state by viewModel.uiState.collectAsState()
    HomeScreen(state)
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState()
) {
    Column {

        val searchedProducts = state.searchedProducts

        val sections = state.sections
        val partnerSections = state.partnerSections


        SearchTextField(
            searchText = state.text,
            onSearchChange = state.onSearchChange


        )



        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            if (state.isShowSections()) {
                for (section in sections) {
                    val title = section.key
                    val product = section.value
                    item {
                        ProductSection(
                            title = title,
                            produtos = product
                        )
                    }

                }

                for (partner in partnerSections) {
                    val title = partner.key
                    val shop = partner.value
                    item {
                        PartnerSection(
                            title = title,
                            shop = shop
                        )
                    }

                }


            } else {
                items(searchedProducts) { p ->
                    CardProductItem(
                        product = p,
                        Modifier.padding(horizontal = 16.dp)
                    )
                }

            }


        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    IfomeTheme() {
        Surface() {
            HomeScreen(
                HomeScreenUiState(
                    sections = sampleSections,
                    partnerSections = sampleShopSections
                )
            )
        }

    }


}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreviewSearchText() {
    IfomeTheme() {
        Surface() {
            HomeScreen(
                HomeScreenUiState(
                    sections = sampleSections,
                    partnerSections = sampleShopSections,
                    searchText = "pizza"
                )
            )
        }

    }


}