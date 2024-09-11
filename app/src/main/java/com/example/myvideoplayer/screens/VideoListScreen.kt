package com.example.myvideoplayer.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myvideoplayer.screens.components.VideoItem
import com.example.myvideoplayer.viewmodel.VideoViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun VideoListScreen(navController: NavHostController, modifier: Modifier = Modifier, viewModel: VideoViewModel= hiltViewModel()) {

    val list by viewModel.videoFiles.collectAsState()


    LaunchedEffect(true) {
        viewModel.getVideoFiles()
    }

    if (list.isEmpty()) {
        Text(modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center,text="No video files")
    }else{
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .then(modifier), contentPadding = PaddingValues(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(list){
                VideoItem(videoFile = it, navController = navController)
            }
        }
    }


}