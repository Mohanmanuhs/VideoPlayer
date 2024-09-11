package com.example.myvideoplayer.model



sealed class Screen(val route:String){

    data object HomeScreen:Screen("home_screen")
    data object VideoPlayerScreen:Screen("video_player_screen/{uri}")


}