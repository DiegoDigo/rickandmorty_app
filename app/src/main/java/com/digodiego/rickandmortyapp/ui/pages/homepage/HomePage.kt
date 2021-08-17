package com.digodiego.rickandmortyapp.ui.pages.homepage

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import com.digodiego.rickandmortyapp.model.CharacterUI
import com.digodiego.rickandmortyapp.ui.components.AppTopBarComponent
import com.digodiego.rickandmortyapp.ui.params.DetailParam
import com.digodiego.rickandmortyapp.ui.theme.PrimaryColor
import com.digodiego.rickandmortyapp.ui.theme.SecondaryColor
import com.digodiego.rickandmortyapp.ui.theme.WhiteColor
import com.digodiego.rickandmortyapp.ui.theme.chicleFamily

@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomePageViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            AppTopBarComponent(title = "The Rick and Morty")
        },
        content = {
            ContentPage(navController, viewModel)
        }
    )
}

@Composable
fun ContentPage(navController: NavController, viewModel: HomePageViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PrimaryColor)
            .padding(18.dp)
    ) {
        LazyRow(modifier = Modifier.matchParentSize()) {
            itemsIndexed(viewModel.characters.value) { _, item ->
                CardItem(navController, item)
                Spacer(modifier = Modifier.width(18.dp))
            }
        }
    }
}

@Composable
fun CardItem(navController: NavController, character: CharacterUI) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(18.dp))
            .clickable {
                navController.currentBackStackEntry?.arguments = Bundle().apply {
                    putParcelable("detailParam", DetailParam(character.id, character.name))
                }
                navController.navigate("detail_page")
            },
        backgroundColor = SecondaryColor
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = rememberImagePainter(character.urlImg),
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(363.dp, 363.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = character.name,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(color = WhiteColor, fontSize = 40.sp, fontFamily = chicleFamily)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = character.origin,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(color = WhiteColor, fontSize = 30.sp, fontFamily = chicleFamily)
            )
        }

    }
}
