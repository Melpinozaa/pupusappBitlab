package sv.edu.bitlab.pupusap.Network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import sv.edu.bitlab.pupusap.Models.Orden
import sv.edu.bitlab.pupusap.Models.TakenOrden
import sv.edu.bitlab.pupusap.Models.RellenoWrapper

interface PupusasApiService {
  @GET("rellenos")
  fun getRellenos() : Call<RellenoWrapper>

  @POST("orden")
  fun submitOrden(@Body params: Orden) : Call<Orden>
}