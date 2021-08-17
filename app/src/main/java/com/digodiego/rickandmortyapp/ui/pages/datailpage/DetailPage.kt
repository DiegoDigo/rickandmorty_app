package com.digodiego.rickandmortyapp.ui.pages.datailpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.digodiego.rickandmortyapp.data.models.response.Episode
import com.digodiego.rickandmortyapp.model.CharacterDetailUi
import com.digodiego.rickandmortyapp.model.EpisodeUi
import com.digodiego.rickandmortyapp.ui.components.AppTopBarComponent
import com.digodiego.rickandmortyapp.ui.pages.homepage.ContentPage
import com.digodiego.rickandmortyapp.ui.params.DetailParam
import com.digodiego.rickandmortyapp.ui.theme.PrimaryColor
import com.digodiego.rickandmortyapp.ui.theme.SecondaryColor
import com.digodiego.rickandmortyapp.ui.theme.WhiteColor
import com.digodiego.rickandmortyapp.ui.theme.chicleFamily

@Composable
fun DetailPage(detailParam: DetailParam, viewModel: DetailPageViewModel = hiltViewModel()) {
    viewModel.getCharacter(detailParam.id)
    val detail = viewModel.character.value
    val loading = viewModel.loading.value

    Scaffold(
        topBar = {
            AppTopBarComponent(
                title = detailParam.name,
                navigationIcon = {
                    IconButton(onClick = {/* Do Something*/ }) {
                        Icon(Icons.Filled.ArrowBack, null, tint = WhiteColor)
                    }
                })
        },
        content = {
            ContentPage(detail, loading)
        }
    )
}


@Composable
fun ContentPage(character: CharacterDetailUi?, loading: Boolean) {
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
        character?.let {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = PrimaryColor)
                    .padding(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CardHeader(character = character)
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(
                        text = "Episodes",
                        style = TextStyle(
                            color = WhiteColor,
                            fontSize = 30.sp,
                            fontFamily = chicleFamily
                        )
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    LazyColumn {
                        itemsIndexed(character.episodes) { _, item ->
                            Column {
                                CardEpisodes(item)
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
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


@Composable
fun CardHeader(character: CharacterDetailUi) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(144.dp)
            .clip(shape = RoundedCornerShape(18.dp)),
        backgroundColor = SecondaryColor
    ) {
        Row {
            Image(
                painter = rememberImagePainter(character.urlImg),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(144.dp, 144.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(18.dp)
            ) {
                Row {
                    Text(
                        text = "Status: ",
                        style = TextStyle(
                            color = WhiteColor,
                            fontSize = 25.sp,
                            fontFamily = chicleFamily
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = character.status,
                        style = TextStyle(
                            color = WhiteColor,
                            fontSize = 25.sp,
                            fontFamily = chicleFamily
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(
                        text = "Species: ",
                        style = TextStyle(
                            color = WhiteColor,
                            fontSize = 25.sp,
                            fontFamily = chicleFamily
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = character.species,
                        style = TextStyle(
                            color = WhiteColor,
                            fontSize = 25.sp,
                            fontFamily = chicleFamily
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(
                        text = "Gender: ",
                        style = TextStyle(
                            color = WhiteColor,
                            fontSize = 25.sp,
                            fontFamily = chicleFamily
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = character.gender,
                        style = TextStyle(
                            color = WhiteColor,
                            fontSize = 25.sp,
                            fontFamily = chicleFamily
                        )
                    )
                }

            }
        }
    }
}