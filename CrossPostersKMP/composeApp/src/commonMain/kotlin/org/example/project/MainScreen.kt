package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.crosspostersandroid.PostersViewModel
import crossposterskmp.composeapp.generated.resources.Res
import crossposterskmp.composeapp.generated.resources.poster_placeholder
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainScreen(paddingValues: PaddingValues, viewModel: PostersViewModel = remember { PostersViewModel() }) {

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
                KamelImage(
                    resource = asyncPainterResource(item.poster?.url?:""),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(bottom = 2.dp, start = 2.dp, end = 2.dp, top = 8.dp)
                        .size(300.dp),
                    onFailure = { Image(painterResource(Res.drawable.poster_placeholder), contentDescription = "Profile") },
                    onLoading = { Image(painterResource(Res.drawable.poster_placeholder), contentDescription = "Profile") }
                )
//                GlideImage(
//                    model = item.poster?.url?:R.drawable.poster_placeholder,
//                    contentDescription = "",
//                    loading = placeholder(R.drawable.poster_placeholder),
//                    failure = placeholder(R.drawable.poster_placeholder),
//                    alignment = Alignment.Center,
//                    modifier = Modifier
//                        .padding(bottom = 2.dp, start = 2.dp, end = 2.dp, top = 8.dp)
//                        .size(300.dp)
//                )
            }
        }

    }
}