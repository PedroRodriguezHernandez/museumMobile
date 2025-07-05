package com.example.museummobile.ui.features.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.museummobile.R
import com.example.museummobile.core.model.News
import com.example.museummobile.core.supabase.MuseumSupabase
import com.example.museummobile.ui.viewModels.MuseumViewModel

@Composable
fun ColumNews(news: SnapshotStateList<News>, navController: NavController) {

    val museumViewModel = remember { MuseumViewModel(MuseumSupabase()) }
    var museums = museumViewModel.museums

    var selectedMuseumIds by remember { mutableStateOf(setOf<String>()) }
    LaunchedEffect(Unit) {
        museumViewModel.loadMuseums()
    }

    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        item {
            MuseumFilterDropdown(
                museums = museums,
                selectedMuseumIds = selectedMuseumIds,
                onSelectionChange = { selectedMuseumIds = it }
            )
            Text(
                text = stringResource(R.string.news),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        val filteredNews = news.filter { item ->
            item.museum_id == null || item.museum_id in selectedMuseumIds
        }

        items(filteredNews) { item ->
            NewsItem(item, navController)
        }
    }
}
