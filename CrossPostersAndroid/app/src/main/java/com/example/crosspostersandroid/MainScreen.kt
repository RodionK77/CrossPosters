package com.example.crosspostersandroid

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainScreen(paddingValues: PaddingValues, viewModel: PostersViewModel = viewModel()) {

    //viewModel.refreshPosters()
    val posters by viewModel.posters.collectAsState()

    Column(
        Modifier.fillMaxSize().padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(posters.docs) { item ->
                GlideImage(
                    model = item.poster?.url?:R.drawable.poster_placeholder,
                    contentDescription = "",
                    loading = placeholder(R.drawable.poster_placeholder),
                    failure = placeholder(R.drawable.poster_placeholder),
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .padding(bottom = 2.dp, start = 2.dp, end = 2.dp, top = 8.dp)
                        .size(300.dp)
                )
            }
        }

    }
}