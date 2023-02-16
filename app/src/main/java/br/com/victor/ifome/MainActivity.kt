package br.com.victor.ifome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import br.com.victor.ifome.sampledata.sampleSections
import br.com.victor.ifome.ui.screens.HomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()


        }

    }
}

@Composable
fun App() {
    Surface {
        HomeScreen(sections = sampleSections)


    }

}







