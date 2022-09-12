package hr.algebra.futurama.api

import retrofit2.Call
import retrofit2.http.GET

const val API_URL = "https://futuramaapi.herokuapp.com/api/"

interface FuturamaApi {
    @GET("v2/characters")
    fun fetchItems(): Call<List<FuturamaItem>>
}