package com.digodiego.rickandmortyapp.ui.pages.homepage

import android.os.Bundle
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.digodiego.rickandmortyapp.R
import com.digodiego.rickandmortyapp.model.CharacterUI
import com.digodiego.rickandmortyapp.ui.components.AppTopBarComponent
import com.digodiego.rickandmortyapp.ui.params.DetailParam
import com.digodiego.rickandmortyapp.ui.theme.PrimaryColor
import com.digodiego.rickandmortyapp.ui.theme.SecondaryColor
import com.digodiego.rickandmortyapp.ui.theme.WhiteColor
import com.digodiego.rickandmortyapp.ui.theme.chicleFamily
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomePageViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppTopBarComponent(
                title = "The Rick and Morty",
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, null, tint = WhiteColor)
                    }
                })
        },
        content = {
            ContentPage(navController, viewModel)
        },

        drawerContent = {
            NavDrawer(scaffoldState)
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
        Box(
            Modifier
                .fillMaxSize()
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
                Box(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxSize()

                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = character.name,
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                color = WhiteColor,
                                fontSize = 40.sp,
                                fontFamily = chicleFamily
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = character.origin,
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                color = WhiteColor,
                                fontSize = 30.sp,
                                fontFamily = chicleFamily
                            )
                        )
                    }

                }

            }
        }

    }
}

@Composable
fun NavDrawer(scaffoldState: ScaffoldState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PrimaryColor)
            .padding(18.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "logo app",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(300.dp)
                    .height(120.dp)
            )
            Divider()
            NavOptions(title = "Home", painterResource(id = R.drawable.ic_home) , scaffoldState = scaffoldState)
            NavOptions(title = "Episodes", painterResource(id = R.drawable.ic_tv) , scaffoldState = scaffoldState)
            NavOptions(title = "Planets", painterResource(id = R.drawable.ic_planets) , scaffoldState = scaffoldState)
        }
    }

}


@Composable
fun NavOptions(title: String, painter: Painter, scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            painter = painter,
            title,
            tint = WhiteColor, modifier = Modifier.size(25.dp, 25.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                },
            style = TextStyle(
                color = WhiteColor,
                fontSize = 30.sp,
                fontFamily = chicleFamily
            )
        )
    }

}