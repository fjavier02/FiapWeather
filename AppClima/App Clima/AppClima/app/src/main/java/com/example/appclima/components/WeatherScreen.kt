package com.example.appclima.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appclima.R
import com.example.appclima.functions.DateUtil
import android.util.Log


@Composable
fun WeatherScreen(
    location: String,
    temperature: String,
    description: String,
    humidity: String,
    windSpeed: String,
    rainProbability: String,
    nextDaysForecast: List<String>,
    modifier: Modifier = Modifier
) {
    Log.d("WeatherScreen", "Location: $location, Temperature: $temperature, Description: $description, Humidity: $humidity, Wind Speed: $windSpeed, Rain Probability: $rainProbability, Next Days: $nextDaysForecast")

    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFF9561A1), Color(0xFF122259)),
        start = Offset(0f, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
        ) {
            // Header
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(color = Color.White)
                    .padding(top = 25.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
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

                Text(
                    text = "Olá, ${DateUtil.getMensagemEntrada()}",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }

            // Caixa de clima principal
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Card(
                    modifier = Modifier
                        .offset(y = (-15).dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xfff9f6f6)),
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
                            val formattedDate = DateUtil.getFormattedDate()
                            Text(text = "$location - $formattedDate",
                                fontSize = 20.sp
                            )
                            Image(
                                painter = painterResource(id = R.drawable.clear), // Atualize o ícone com base na descrição do clima
                                contentDescription = "Clima",
                                modifier = Modifier.size(200.dp)
                            )
                            Text(
                                text = temperature,
                                modifier = Modifier.padding(top = 20.dp),
                                fontSize = 40.sp
                            )
                            Text(
                                text = description,
                                fontSize = 30.sp
                            )

                            Row(
                                modifier = Modifier.padding(top = 50.dp),
                                horizontalArrangement = Arrangement.spacedBy(40.dp)
                            ) {
                                WeatherDetailCard(
                                    iconRes = R.drawable.humidity,
                                    text = humidity,
                                    contentDescription = "Umidade"
                                )
                                WeatherDetailCard(
                                    iconRes = R.drawable.mist,
                                    text = windSpeed,
                                    contentDescription = "Velocidade do vento"
                                )
                                WeatherDetailCard(
                                    iconRes = R.drawable.rain,
                                    text = rainProbability,
                                    contentDescription = "Probabilidade de chuva"
                                )
                            }

                            Text(
                                text = "Previsão para os próximos dias:",
                                modifier = Modifier.padding(top = 30.dp),
                                fontSize = 24.sp
                            )

                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                items(nextDaysForecast) { day ->
                                    DayForecastCard(day = day)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherDetailCard(
    iconRes: Int,
    text: String,
    contentDescription: String
) {
    Card {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = contentDescription,
                modifier = Modifier.size(70.dp)
            )
            Text(text = text)
        }
    }
}

@Composable
fun DayForecastCard(day: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .width(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xfff9f6f6)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = day,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "MAX")
            Text(text = "MIN")
        }
    }
}