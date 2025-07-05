package com.example.museummobile.ui.features.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.museummobile.R
import com.example.museummobile.core.supabase.NewsSupabase
import com.example.museummobile.ui.features.home.convertText
import com.example.museummobile.ui.viewModels.NewsViewModel

@Composable
fun News(id: String, navController: NavHostController) {
    val scrollState = rememberScrollState()
    val newsViewModel = remember { NewsViewModel(NewsSupabase()) }
    val news = newsViewModel.single_news

    LaunchedEffect(Unit){
        newsViewModel.loadNews(id)
    }

    Column(modifier = Modifier.fillMaxSize()
        .background(color = colorResource(R.color.broken_withe))
        .statusBarsPadding()
        .navigationBarsPadding()
    ) {

        Row {
           IconButton(onClick = { navController.popBackStack() }) {
               Icon(imageVector = Icons.Default.ArrowBackIosNew,
                   contentDescription = stringResource(R.string.go_back)
               )
           }
       }
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.white))
            .padding(20.dp)
            .verticalScroll(scrollState)
        ) {
            Text(
                text = news.value?.title ?: "",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = news.value?.start_date.toString() ?: "",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )


            Spacer(modifier = Modifier.height(16.dp))

            val text = news.value?.let { convertText(it.body) }
            Text(
                text = text.toString(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
        }
    }
}


