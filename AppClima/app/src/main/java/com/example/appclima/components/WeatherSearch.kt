package com.example.appclima.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appclima.R

@Composable
fun WeatherAppHome() {

    // Criar as variáveis que serão utilizadas nos cards com os dados provenientes da API

    var city by remember {
        mutableStateOf("")
    }
    
    var tempAtual by remember {
        mutableStateOf("")
    }


    Column( // <-- Coluna com row que possui uma caixa de texto e um botão de pesquisa
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row( // <-- row com caixa de texto e botão de pesquisa
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            OutlinedTextField( // <-- Caixa de texto da pesquisa
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(30.dp),
                value = city,
                onValueChange = { city = it },
                label = {
                    Text(text = "Pesquise uma localização")
                }
            )
            IconButton(
                onClick = { /*TODO*/ }, // <-- Botão de pesquisa que redireciona para a segunda tela
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Botão para pesquisar localização"
                )
            }

        }
        Column { // <-- Column com os cards das cidades
            Spacer(modifier = Modifier.height(20.dp))
            Card( //  <-- Card com local e infos básicas. Precisa transformar os cards em botões.
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xff329F6B)),
                elevation = CardDefaults.cardElevation(4.dp),
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Informações que serão substituídas por dados da API
                        Text(
                            text = "São Paulo", //<-- Trocar a String pela variável que recebe o valor da API
                            modifier = Modifier
                                .padding(top = 30.dp, bottom = 30.dp, start = 20.dp),
                            fontSize = 25.sp
                        )
                        Image( // <-- Criar a lógica que seleciona o ícone em função da temperatura fornecida pela API
                            painter = painterResource(id = R.drawable.cloud),
                            contentDescription = "Ícone de dia nublado",
                            modifier = Modifier.offset(x = 50.dp)
                        )
                        Text(
                            text = "21°C", //<-- Trocar a String pela variável que recebe o valor da API
                            modifier = Modifier.padding(top = 30.dp, bottom = 30.dp, start = 70.dp),
                            fontSize = 25.sp
                        )
                    }

                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                // <-- <-- Card com local e infos básicas
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xff329F6B)),
                elevation = CardDefaults.cardElevation(4.dp),
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Informações que serão substituídas por dados da API
                        Text(
                            text = "Belo Horizonte", //<-- Trocar a String pela variável que recebe o valor da API
                            modifier = Modifier
                                .padding(top = 30.dp, bottom = 30.dp, start = 20.dp),
                            fontSize = 25.sp
                        )
                        Image( // <-- Criar a lógica que seleciona o ícone em função da temperatura fornecida pela API
                            painter = painterResource(id = R.drawable.cloud),
                            contentDescription = "Ícone de dia nublado",
                            modifier = Modifier.offset(x = 18.dp)
                        )
                        Text(
                            text = "23°C", //<-- Trocar a String pela variável que recebe o valor da API
                            modifier = Modifier.padding(top = 30.dp, bottom = 30.dp, start = 20.dp),
                            fontSize = 25.sp
                        )
                    }
                }
            )
        }
    }
}
