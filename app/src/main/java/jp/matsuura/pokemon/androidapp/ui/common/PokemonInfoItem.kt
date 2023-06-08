package jp.matsuura.pokemon.androidapp.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PokemonInfoItem(
    imageRes: Int,
    title: String,
    info: String,
) {
    Box(
        modifier = Modifier
        .fillMaxWidth()
        .background(
            color = Color.White,
            shape = RoundedCornerShape(topStart = 60.dp, bottomStart = 60.dp),
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(16.dp)
        ) {
            Image(
                modifier = Modifier.padding(start = 16.dp),
                painter = painterResource(id = imageRes),
                contentDescription = null,
            )
            Text(modifier = Modifier.padding(start = 16.dp), text = title, fontSize = 18.sp)
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                text = info,
                fontSize = 18.sp,
                textAlign = TextAlign.End
            )
        }
    }
}