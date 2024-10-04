package com.example.crosspostersandroid

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

private const val apiUrl = "https://api.kinopoisk.dev/v1.4/movie?page=1&limit=120&selectFields=poster&type=movie&rating.kp=8.0-10&votes.kp=1000000-50000000&lists=top250&token=${Tokens.TOKEN1}"

class PostersViewModel : ViewModel() {
    private val _posters = MutableStateFlow<PosterItemResponse>(PosterItemResponse())
    internal val posters: StateFlow<PosterItemResponse> = _posters

    private val client = HttpClient(CIO) {
        install(ContentNegotiation){
            json(
                Json{
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
    }

    suspend fun getAll(): PosterItemResponse {
        val response: PosterItemResponse = client.get(apiUrl).body()
        Log.d("R", "респонс")
        Log.d("R", response.toString())
        //val jsonString = response.toString()
        //val dto = json.decodeFromString<List<PosterItem>>(jsonString)
        //Log.d("R", dto.toString())
        return response
    }


    init{

        viewModelScope.launch {
            _posters.value = getAll()
            Log.d("R","ОНО",)
            Log.d("R", _posters.value.toString(),)
        }
    }
}