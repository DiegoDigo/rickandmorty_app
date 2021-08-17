package com.digodiego.rickandmortyapp.ui.pages

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.digodiego.rickandmortyapp.ui.pages.datailpage.DetailPage
import com.digodiego.rickandmortyapp.ui.pages.homepage.HomePage
import com.digodiego.rickandmortyapp.ui.params.DetailParam
import com.digodiego.rickandmortyapp.ui.theme.PrimaryColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContent {
                val navController = rememberNavController()

                SystemUi(windows = window)
                NavHost(navController, startDestination = "home_page") {
                    composable("home_page") {
                        HomePage(navController)
                    }
                    composable("detail_page") {
                        val itemsProvider = navController.previousBackStackEntry?.arguments?.getParcelable<DetailParam>("detailParam")
                        itemsProvider?.let { items ->
                            DetailPage(detailParam = items)
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun SystemUi(windows: Window) =
    MaterialTheme {
        windows.statusBarColor = PrimaryColor.toArgb()
        windows.navigationBarColor = PrimaryColor.toArgb()

        @Suppress("DEPRECATION")
        if (PrimaryColor.luminance() > 0.5f) {
            windows.decorView.systemUiVisibility = windows.decorView.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        @Suppress("DEPRECATION")
        if (PrimaryColor.luminance() > 0.5f) {
            windows.decorView.systemUiVisibility = windows.decorView.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }