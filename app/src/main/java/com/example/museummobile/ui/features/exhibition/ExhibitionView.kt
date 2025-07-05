package com.example.museummobile.ui.features.exhibition

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.museummobile.R
import com.example.museummobile.core.supabase.ExhibitionSupabase
import com.example.museummobile.ui.viewModels.ExhibitionViewModel
import kotlinx.serialization.json.JsonPrimitive

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Exhibition(navController: NavController, id: String) {

    val exhibitionViewModel = remember { ExhibitionViewModel(ExhibitionSupabase()) }
    val exhibition by exhibitionViewModel.exhibitionState.collectAsState()
    val loading by exhibitionViewModel.isLoading.collectAsState()

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        exhibitionViewModel.fetchExhibitionById(id)
    }


    if (loading){
        Box (
            modifier = Modifier
                .fillMaxSize(),
        ){
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.broken_withe))
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(color = colorResource(R.color.broken_withe))
                    .statusBarsPadding()
                    .padding(10.dp)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBackIosNew,
                        contentDescription = "Volver"
                    )
                }

                Text(
                    color = colorResource(R.color.dark_blue),
                    text = exhibition?.title ?: "",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .background(color = colorResource(R.color.white))
                    .padding(13.dp)
            ) {
                exhibition?.let {
                    AsyncImage(
                        model = it.image_url,
                        contentDescription = it.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentScale = ContentScale.Fit
                    )


                    it.tags.orEmpty().forEach { (key, value) ->
                        val displayValue = (value as? JsonPrimitive)?.content ?: value.toString()
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("$key: ")
                                }
                                append(displayValue)
                            },
                            fontSize = 16.sp
                        )
                    }

                    val plainText = HtmlCompat.fromHtml(
                        it.description
                            .replace(Regex("&nbsp;|\\u00A0"), " ")
                            .trim(),
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    ).toString()



                    Text(
                        text = plainText,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Justify),
                        overflow = TextOverflow.Clip,
                        softWrap = true
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun PreHome(){
    val navController =  rememberNavController()
    val id = "papaya"
    Exhibition(navController, id)
}