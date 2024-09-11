package com.example.myvideoplayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myvideoplayer.model.VideoFile
import com.example.myvideoplayer.repo.VideoFilesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VideoViewModel @Inject constructor(private val videoFilesRepo: VideoFilesRepo): ViewModel() {
    private val _videoFiles = MutableStateFlow<List<VideoFile>>(emptyList())
    val videoFiles: StateFlow<List<VideoFile>> = _videoFiles.asStateFlow()


    fun getVideoFiles(){
        viewModelScope.launch {
            videoFilesRepo.getVideoFiles().collect{
                _videoFiles.value+=it
            }
        }
    }

}