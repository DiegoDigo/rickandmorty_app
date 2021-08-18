package com.digodiego.rickandmortyapp.ui.pages.locationpages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digodiego.rickandmortyapp.model.EpisodeUi
import com.digodiego.rickandmortyapp.ui.components.AppTopBarComponent
import com.digodiego.rickandmortyapp.ui.theme.PrimaryColor
import com.digodiego.rickandmortyapp.ui.theme.SecondaryColor
import com.digodiego.rickandmortyapp.ui.theme.WhiteColor
import com.digodiego.rickandmortyapp.ui.theme.chicleFamily

@Composable
fun EpisodePage(navController: NavController, viewModel: EpisodesPageViewModel = hiltViewModel()) {

    val episode = viewModel.episodes.value
    val loading = viewModel.loading.value

    Scaffold(
        topBar = {
            AppTopBarComponent(
                title = "Episodes",
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, null, tint = WhiteColor)
                    }
                })
        },
        content = {
            ContentPage(episode, loading)
        }
    )
}


@Composable
fun ContentPage(episode: List<EpisodeUi>, loading: Boolean) {
    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = PrimaryColor)
                .padding(18.dp),

            ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = PrimaryColor)
                .padding(18.dp),

            ) {
            LazyColumn {
                itemsIndexed(episode) { _, item ->
                    CardEpisodes(item)
                    Spacer(modifier = Modifier.height(18.dp))
                }
            }
        }
    }

}


@Composable
fun CardEpisodes(episode: EpisodeUi) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(shape = RoundedCornerShape(18.dp)),
        backgroundColor = SecondaryColor
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = episode.episode,
                    style = TextStyle(
                        color = WhiteColor,
                        fontSize = 20.sp,
                        fontFamily = chicleFamily
                    )
                )
                Text(
                    text = episode.data,
                    style = TextStyle(
                        color = WhiteColor,
                        fontSize = 20.sp,
                        fontFamily = chicleFamily
                    )
                )
            }
            Text(
                text = episode.title,
                style = TextStyle(color = WhiteColor, fontSize = 20.sp, fontFamily = chicleFamily)
            )
        }
    }
}
