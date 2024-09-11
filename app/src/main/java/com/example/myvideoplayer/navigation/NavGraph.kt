package com.example.myvideoplayer.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myvideoplayer.model.Screen
import com.example.myvideoplayer.screens.VideoListScreen
import com.example.myvideoplayer.screens.VideoPlayerScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(Screen.HomeScreen.route){
            VideoListScreen(navController,modifier)
        }
        composable(Screen.VideoPlayerScreen.route, arguments = listOf(navArgument("uri"){
            type= NavType.StringType
        })){ navBackStackEntry->
            val uri = navBackStackEntry.arguments?.getString("uri")
            Log.d("MyUri",uri.toString())
            if (uri != null) {
                VideoPlayerScreen(uri)
            }
        }

    }



}