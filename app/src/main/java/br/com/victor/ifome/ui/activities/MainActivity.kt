package br.com.victor.ifome.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.victor.ifome.dao.ProductDao
import br.com.victor.ifome.sampledata.*
import br.com.victor.ifome.ui.screens.HomeScreen
import br.com.victor.ifome.ui.states.HomeScreenUiState
import br.com.victor.ifome.ui.theme.IfomeTheme
import br.com.victor.ifome.ui.viewmodels.HomeScreenViewModel


class MainActivity : ComponentActivity() {
    private val dao = ProductDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFabClick = {
                startActivity(
                    Intent(
                        this,
                        FormularioProdutoActivity::class.java
                    )
                )
            }, content = {



                val viewModel by viewModels<HomeScreenViewModel>()

                HomeScreen(
                    viewModel
                )
            })


        }

    }
}

@Composable
fun App(
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    IfomeTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(
                    onClick = onFabClick,
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }


            }) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }

            }


        }
    }


}

@Preview
@Composable
fun AppPreview() {
    App {
        HomeScreen(
            state = HomeScreenUiState(
                sections = sampleSections,
                partnerSections = sampleShopSections
            )
        )
    }
}







