package com.ismartcoding.plain.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AudioFile
import androidx.compose.material.icons.outlined.FilePresent
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.VideoFile
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ismartcoding.plain.R
import com.ismartcoding.plain.ui.audio.AudiosDialog
import com.ismartcoding.plain.ui.base.GridItem
import com.ismartcoding.plain.ui.base.Subtitle
import com.ismartcoding.plain.ui.file.FilesDialog
import com.ismartcoding.plain.ui.image.ImagesDialog
import com.ismartcoding.plain.ui.video.VideosDialog

@Composable
fun HomeItemStorage(
    navController: NavHostController,
) {
    Column {
        Subtitle(
            text = stringResource(R.string.home_item_title_storage)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            GridItem(icon = Icons.Outlined.Image, stringResource(id = R.string.images), modifier = Modifier.weight(1f)) {
                ImagesDialog().show()
            }
            Spacer(modifier = Modifier.width(8.dp))
            GridItem(icon = Icons.Outlined.AudioFile, stringResource(id = R.string.audios), modifier = Modifier.weight(1f)) {
                AudiosDialog().show()
            }
            Spacer(modifier = Modifier.width(8.dp))
            GridItem(icon = Icons.Outlined.VideoFile, stringResource(id = R.string.videos), modifier = Modifier.weight(1f)) {
                VideosDialog().show()
            }
            Spacer(modifier = Modifier.width(8.dp))
            GridItem(icon = Icons.Outlined.FilePresent, stringResource(id = R.string.files), modifier = Modifier.weight(1f)) {
                FilesDialog().show()
            }
        }
    }
}


