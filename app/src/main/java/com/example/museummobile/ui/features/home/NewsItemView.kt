package com.example.museummobile.ui.features.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import com.example.museummobile.R
import com.example.museummobile.core.model.News
import com.example.museummobile.navegation.Screen

@Composable
fun NewsItem(news: News, navController: NavController){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(110.dp)
            .border(
                width = 1.dp,
                color = colorResource(R.color.ultra_light_blue),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
                navController.navigate(Screen.News.createRouter(news.id))
            },
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.broken_withe)),
        shape = RoundedCornerShape(15.dp)
    ) {
        val text = convertText(news.body)
        Column(modifier = Modifier
            .padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = news.start_date.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = text.toString(),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

fun convertText(body: String): Any {
    val plainText = HtmlCompat.fromHtml(
        body
            .replace(Regex("&nbsp;|\\u00A0"), " ")
            .trim(),
        HtmlCompat.FROM_HTML_MODE_LEGACY
    ).toString()
    return plainText
}
