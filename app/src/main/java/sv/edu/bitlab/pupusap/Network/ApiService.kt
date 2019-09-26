package sv.edu.bitlab.pupusap.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {
  companion object {
    fun create(): PupusasApiService {

      //region Logging de llamadas

      val httpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)

      val logging = HttpLoggingInterceptor()
      logging.setLevel(HttpLoggingInterceptor.Level.BODY)
      //endregion

      //region inicializacion de Retrofit

      val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://bitlab-dam.herokuapp.com/")
        .client(httpClient.build())
        .build()
      //endregion

      //region retorna servicio
      return retrofit.create(PupusasApiService::class.java)
      //endregion
    }
  }
}