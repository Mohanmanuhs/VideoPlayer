package com.example.myvideoplayer.screens.components

import android.net.Uri
import android.os.Build
import android.util.Size
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myvideoplayer.R
import com.example.myvideoplayer.model.Screen
import com.example.myvideoplayer.model.VideoFile
import com.example.myvideoplayer.utils.toFormattedSize
import com.example.myvideoplayer.utils.toFormattedTime

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun VideoItem(navController: NavHostController, modifier: Modifier = Modifier, videoFile: VideoFile) {
    Column(
        modifier = Modifier.clickable {
            val routes = Screen.VideoPlayerScreen.route.replace("{uri}",
                Uri.encode(videoFile.pathUri.toString())
            )
            navController.navigate(routes)
        }
    ) {
        Row(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .width(180.dp)
                    .fillMaxWidth()
            ) {
                val thumbnailImage = if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q) {
                    LocalContext.current.contentResolver.loadThumbnail(
                        videoFile.pathUri,
                        Size(640, 480), null
                    )
                }else{
                    null
                }
                Box(modifier = Modifier.fillMaxHeight()) {
                    if(thumbnailImage!=null){
                        Image(
                            modifier = Modifier.fillMaxHeight(),
                            bitmap = thumbnailImage.asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }else{
                        Image(
                            modifier = Modifier.fillMaxHeight(),
                            painter = painterResource(id = R.drawable.image_placeholder),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = videoFile.duration.toFormattedTime(), modifier = Modifier
                            .padding(8.dp)
                            .background(Color.Black)
                            .padding(horizontal = 4.dp)
                            .align(Alignment.BottomEnd), color = Color.White
                    )
                }
            }
            Column(modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Center) {
                Text(text = videoFile.name,
                    style = MaterialTheme.typography.bodyLarge)
                Text(text = videoFile.size.toFormattedSize(), modifier = Modifier.padding(top=8.dp),
                    style = MaterialTheme.typography.bodySmall)

            }
        }
    }
}