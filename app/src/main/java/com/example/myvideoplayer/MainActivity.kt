package com.example.myvideoplayer

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.myvideoplayer.navigation.NavGraph
import com.example.myvideoplayer.ui.theme.MyVideoPlayerTheme
import com.example.myvideoplayer.utils.askPermissions
import com.example.myvideoplayer.utils.checkPermissions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isGranted by remember {
                mutableStateOf(false)
            }
            MyVideoPlayerTheme {
                val navController = rememberNavController()
                Scaffold {
                    Box(modifier = Modifier.fillMaxSize()){

                        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            android.Manifest.permission.READ_MEDIA_VIDEO
                        } else {
                            android.Manifest.permission.READ_EXTERNAL_STORAGE
                        }

                        isGranted = checkPermissions(permissions)

                        if (isGranted) {
                            NavGraph(navController,modifier = Modifier.padding(it))
                        }else{
                            Button(modifier = Modifier.align(Alignment.Center)
                                ,onClick = {
                                    askPermissions(
                                        permissions,
                                        onPermissionGranted = {
                                            isGranted = true
                                        },
                                        onPermissionDenied = {
                                            isGranted = false
                                            Toast.makeText(this@MainActivity, "please allow permissions", Toast.LENGTH_SHORT).show()
                                        },
                                        onPermissionsResultCallback = {}
                                    )
                                }) {
                                Text(text = "Allow Permissions")

                            }
                        }
                    }

                }
            }
        }
    }
}
