package com.example.listaalumnosrandom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    val listaInicial = mutableListOf<String>("Carlos0", "Carlos1", "David Salvador", "Giovanni", "Iván", "Daniel", "David Romero")
    var lista by remember { mutableStateOf(listaInicial.toMutableList()) }
    var nombre by remember { mutableStateOf("")}

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text( // Título
            text = "Lista de Alumnos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(210.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box (
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(12.dp)
            ){
                LazyColumn { // LazyColumn para que tenga scroll interno si no caben los elementos
                    items(lista) { alumno ->
                        Text(
                            text = alumno,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        )
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            if (lista.isNotEmpty()) {
                val randomPos = (0 until lista.size).random()
                nombre = lista.get(randomPos)
                // Actualiza la lista
                lista = lista.toMutableList().apply { removeAt(randomPos) }

            }
        }) {
            Text("Elegir alumno")
        }

        // Botón reiniciar
        OutlinedButton(onClick = {
            lista = listaInicial.toMutableList()
            nombre = ""
        }) {
            Text("Reiniciar lista")
        }

        // Nombre elegido
        AnimatedVisibility(visible = nombre.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(0.6f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = nombre,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListaAlumnosCard()
}