package com.example.appclima.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appclima.model.WeatherCardData
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.appclima.R


@Composable
fun WeatherCard(data: WeatherCardData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xff329F6B)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = data.city,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 30.dp, start = 20.dp),
                fontSize = 25.sp,
                color = Color.White
            )
            Image(
                painter = painterResource(id = R.drawable.clear),
                contentDescription = "Descrição da imagem",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp)
            )

            Text(
                text = data.temperature,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 30.dp, start = 20.dp)
                    .align(Alignment.CenterVertically),
                fontSize = 25.sp,
                color = Color.White
            )
        }
    }
}