package hr.algebra.futurama.api

import android.content.Context
import android.util.Log
import hr.algebra.futurama.FuturamaReceiver
import hr.algebra.futurama.framework.sendBroadcast
import hr.algebra.futurama.handler.downloadImageAndStore
import hr.algebra.futurama.model.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FuturamaFetcher(private val context: Context) {
    private var futuramaApi: FuturamaApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        futuramaApi = retrofit.create(FuturamaApi::class.java)
    }

    fun fetchItems() {
        val request = futuramaApi.fetchItems()
        request.enqueue(object : Callback<List<FuturamaItem>> {
            override fun onResponse(
                call: Call<List<FuturamaItem>>,
                response: Response<List<FuturamaItem>>
            ) {
                response.body()?.let {
                    populateItems(it)
                }
            }

            override fun onFailure(call: Call<List<FuturamaItem>>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }

        })
    }

    private fun populateItems(futuramaItems: List<FuturamaItem>) {

        GlobalScope.launch {
            val items = mutableListOf<Item>()
            futuramaItems.forEach {
                val picturePath = downloadImageAndStore(
                    context,
                    it.picUrl,
                    it.name.replace(" ", "_")
                )
                items.add(
                    Item(
                        null,
                        it.species,
                        it.age,
                        it.planet,
                        picturePath ?: "",
                        it.name,
                        false
                    )
                )
            }
            //setBooleanPreference(DATA_IMPORTED, true)
            context.sendBroadcast<FuturamaReceiver>()
        }
    }
}