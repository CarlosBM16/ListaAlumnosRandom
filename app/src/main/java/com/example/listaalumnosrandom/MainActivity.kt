package com.example.listaalumnosrandom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listaalumnosrandom.ui.theme.ListaAlumnosRandomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaAlumnosRandomTheme {ListaAlumnosCard()
            }
        }
    }
}

@Composable
fun ListaAlumnosCard() {
    val listaInicial = listOf<String>("Carlos0", "Carlos1", "David Salvador", "Giovanni", "Iván", "Daniel", "David Romero")
    var lista by remember { mutableStateOf(listaInicial) }
    var randomPos : Int = -1
    var nombre by remember { mutableStateOf("")}

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box (
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        ){
            Column {
                lista.forEach { nombre ->
                    Text(text = nombre)
                }
            }

        }

        Button(onClick = {
            randomPos = (0..(lista.size - 1)).random()
            nombre = lista.get(randomPos)

        }) {
            Text("Elegir alumno")
        }

        Text(nombre)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListaAlumnosCard()
}