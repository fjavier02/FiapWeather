package com.example.appclima.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appclima.R
import com.example.appclima.functions.DateUtil
import com.example.appclima.functions.MensagemEntrada
import java.time.LocalDate

@Composable
fun WeatherScreen(modifier: Modifier = Modifier) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFF9561A1), Color(0xFF122259)),
        start = Offset(0f, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = gradientBrush) // Aplica o gradiente como fundo

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        ) {
            //Header
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(colorResource(id = R.color.white))
                    .padding(top = 25.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo",
                        modifier = Modifier.size(40.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "FiapWeather",
                        fontSize = 30.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,

                        )
                }
                val msgEntrada = MensagemEntrada.getMensagemEntrada()
                Text(
                    text = "Olá, ${msgEntrada}",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 20.dp)
                )

            }
            //Caixa de clima principal
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Card(
                    modifier = Modifier
                        .offset(y = (-15).dp)
                        .fillMaxWidth()
                    ,
                    colors = CardDefaults
                        .cardColors(containerColor = Color(0xfff9f6f6)),
                    elevation = CardDefaults.cardElevation(4.dp),

                    ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                    ) {

                        Column(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 25.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            val dataFormatada = DateUtil.getFormattedDate()
                            //ALTERAR COM A API
                            Text(text = "LOCALIZAÇÂO - ${dataFormatada}",
                                fontSize = 20.sp)
                            //ALTERAR CONFORME CLIMA
                            Image(
                                painter = painterResource(id = R.drawable.clear),
                                contentDescription = "Clima img",
                                modifier = Modifier.size(200.dp)
                            )
                            //ALTERAR
                            Text(text = "30ºc",
                                modifier = Modifier.padding(top = 20.dp),
                                fontSize = 40.sp)

                            Text(text = "MIN-MAX",
                                modifier = Modifier,
                                fontSize = 30.sp)

                            Row (modifier = Modifier.padding(top = 50.dp),
                                horizontalArrangement = Arrangement.spacedBy(40.dp)
                            ){
                                Card() {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        //UMIDADE
                                        Image(painter = painterResource(id = R.drawable.humidity),
                                            contentDescription = "umidade",
                                            Modifier.size(70.dp))
                                        Text(text = "%")
                                    }
                                }

                                Card {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Image(painter = painterResource(id = R.drawable.mist),
                                            contentDescription = "",
                                            Modifier.size(50.dp))
                                        //VELOCIDADE DO VENTO
                                        Text(text = "KM/H",
                                            Modifier.padding(all = 10.dp),
                                            textAlign = TextAlign.Center)
                                    }
                                }
                                Card {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Image(painter = painterResource(id = R.drawable.rain),
                                            contentDescription ="chuva",
                                            Modifier.size(50.dp))
                                        //POCENTAGEM DE CHUVA
                                        Text(text = "%",
                                            Modifier.padding(all = 10.dp),
                                            textAlign = TextAlign.Center)
                                    }
                                }
                            }
                            Text(text = "Previsão para os proximos dias:",
                                modifier = Modifier.padding(top = 30.dp), fontSize = 24.sp)

                            val today = LocalDate.now()
                            val nextDays = DateUtil.getNextDaysOfWeek(today, 3)
                            Row {
                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    items(nextDays) { day ->
                                        Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 4.dp),
                                            colors = CardDefaults.cardColors(containerColor = Color(0xfff9f6f6)),
                                            elevation = CardDefaults.cardElevation(4.dp)
                                        ) {
                                            Column(
                                                modifier = Modifier.padding(16.dp)
                                            ) {
                                                Text(
                                                    text = day,
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Column {
                                                    Text(text = "MAX")
                                                    Text(text = "MIN")
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}
