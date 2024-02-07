package com.example.myapplication.articlesViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.articlesModel.NewsResponse
import com.example.myapplication.utils.ResultOf
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ArticlesViewModel : ViewModel() {

    val newsResponse: MutableLiveData<ResultOf<NewsResponse?>> = MutableLiveData()

    fun getAllArticles() {
        newsResponse.value = ResultOf.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            var httpURLConnection: HttpURLConnection? = null
            try {
                val url =
                    URL("https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json")

                httpURLConnection = withContext(Dispatchers.IO) {
                    url.openConnection()
                } as HttpURLConnection

                if (httpURLConnection.responseCode == 200) {

                    if (httpURLConnection.inputStream.toString().isNotEmpty()) {
                        val bufferedReader = BufferedReader(
                            InputStreamReader(httpURLConnection.inputStream)
                        )

                        val resultantJsonString = StringBuilder()

                        while (true) {
                            val inputStream = withContext(Dispatchers.IO) {
                                bufferedReader.readLine()
                            } ?: break
                            resultantJsonString.append(inputStream)

                            Log.d("viewModel", "getAllArticles in Json: $resultantJsonString")
                        }
                        getNewsResults(resultantJsonString)


                    } else {
                        newsResponse.postValue(ResultOf.Failure(null))
                        throw IOException("incorrect json stream: ${httpURLConnection.errorStream}")
                    }
                } else {
                    newsResponse.postValue(ResultOf.Failure(null))
                    throw IOException("incorrect response code: ${httpURLConnection.responseCode}")
                }


            } catch (ioException: IOException) {
                newsResponse.postValue(ResultOf.Failure(null))
                Log.e("viewModel", "makeGetHttpRequest status: ${ioException.message}")
            } finally {
                httpURLConnection?.disconnect()
            }
        }
    }

    private fun getNewsResults(resultantJsonString: StringBuilder) {
        viewModelScope.launch(Dispatchers.Main) {
            val newsResult = Gson().fromJson(
                resultantJsonString.toString(), NewsResponse::class.java
            )
            Log.d("viewModel", "newsResponse: $newsResult")

//            newsResponse.value = newsResult
            newsResponse.postValue(ResultOf.Success(newsResult))
        }
    }


}