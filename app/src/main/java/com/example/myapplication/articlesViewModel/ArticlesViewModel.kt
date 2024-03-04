package com.example.myapplication.articlesViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.articlesModel.NewsResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ArticlesViewModel : ViewModel() {

    private var _newsResponse: MutableLiveData<NewsResponse?> = MutableLiveData()

    val newsResponse: LiveData<NewsResponse?> = _newsResponse

    fun getAllArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            var httpURLConnection: HttpURLConnection? = null
            try {
                val url =
                    URL("https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json")

                httpURLConnection = url.openConnection() as HttpURLConnection

                if (httpURLConnection.responseCode == 200) {

                    if (httpURLConnection.inputStream.toString().isNotEmpty()) {
                        val bufferedReader = BufferedReader(
                            InputStreamReader(httpURLConnection.inputStream)
                        )

                        val resultantJsonString = StringBuilder()

                        while (true) {
                            val inputStream = bufferedReader.readLine() ?: break
                            resultantJsonString.append(inputStream)

                            Log.d("viewModel", "getAllArticles in Json: $resultantJsonString")
                        }
                        getNewsResults(resultantJsonString)


                    } else {
                        _newsResponse.postValue(null)
                        throw IOException("incorrect json stream: ${httpURLConnection.errorStream}")
                    }
                } else {
                    _newsResponse.postValue(null)
                    throw IOException("incorrect response code: ${httpURLConnection.responseCode}")
                }


            } catch (ioException: IOException) {
                _newsResponse.postValue(null)
                Log.e("viewModel", "makeGetHttpRequest status: ${ioException.message}")
            } finally {
                httpURLConnection?.disconnect()
            }
        }
    }

    private fun getNewsResults(resultantJsonString: StringBuilder) {
        viewModelScope.launch(Dispatchers.IO) {
            val newsResult = Gson().fromJson(
                resultantJsonString.toString(), NewsResponse::class.java
            )
            Log.d("viewModel", "_newsResponse: $newsResult")

            _newsResponse.postValue(newsResult)
        }
    }


}