package com.example.museummobile.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museummobile.core.domain.NewsRepository
import com.example.museummobile.core.model.News
import com.example.museummobile.core.supabase.SupabaseClientProvider
import com.example.museummobile.core.supabase.SupabaseClientProvider.supabase
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.github.jan.supabase.realtime.postgresListDataFlow
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NewsViewModel (
    private val newsRepository: NewsRepository
) : ViewModel(){

    var news = mutableStateListOf<News>()
    var single_news = mutableStateOf<News?>(null)

    var errorMessage by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)
        private set

    fun loadNews(){
        viewModelScope.launch (){
            isLoading = true
            errorMessage = null
            try {
                val result = newsRepository.getNews()
                news.clear()
                news.addAll(result)
            } catch (e : Exception){
                errorMessage = e.message
            } finally {
                isLoading = false
            }

        }
    }


    fun loadNews(id:String){
        viewModelScope.launch (){
            isLoading = true
            errorMessage = null
            try {
                val result = newsRepository.getNewsById(id)
                single_news.value = result
            } catch (e : Exception){
                errorMessage = e.message
            } finally {
                isLoading = false
            }

        }
    }
}