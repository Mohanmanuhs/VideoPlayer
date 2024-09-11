package com.example.myvideoplayer.repo

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore.Video.Media
import com.example.myvideoplayer.model.VideoFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VideoFilesRepo(private val context: Context) {

    fun getVideoFiles():Flow<VideoFile> = flow {

        val contentResolver = context.contentResolver?:return@flow

        val projection = arrayOf(
            Media.DISPLAY_NAME,
            Media.DURATION,
            Media.SIZE,
            Media._ID

        )
        contentResolver.query(
            Media.EXTERNAL_CONTENT_URI,
            projection,
            null,null,"${Media.DATE_ADDED} DESC"
        )?.use{cursor->
            while(cursor.moveToNext()){
                val displayNameIdx = cursor.getColumnIndexOrThrow(Media.DISPLAY_NAME)
                val durationIdx = cursor.getColumnIndexOrThrow(Media.DURATION)
                val sizeIdx = cursor.getColumnIndexOrThrow(Media.SIZE)
                val idIdx = cursor.getColumnIndexOrThrow(Media._ID)

                val displayName = cursor.getString(displayNameIdx)
                val duration = cursor.getLong(durationIdx)
                val size = cursor.getLong(sizeIdx)
                val id = cursor.getLong(idIdx)

                val videoUri = ContentUris.withAppendedId(Media.EXTERNAL_CONTENT_URI,id)

                val videoFile = VideoFile(
                    name = displayName,
                    duration = duration,
                    pathUri = videoUri,
                    size = size
                )
                emit(videoFile)

            }
        }

    }
}