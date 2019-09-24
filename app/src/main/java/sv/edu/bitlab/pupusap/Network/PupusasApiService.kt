package sv.edu.bitlab.pupusap.Network

import retrofit2.Call
import retrofit2.http.*
import sv.edu.bitlab.pupusap.Models.Orden
import sv.edu.bitlab.pupusap.Models.TakenOrden
import sv.edu.bitlab.pupusap.Models.RellenoWrapper

interface PupusasApiService {
  @GET("rellenos")
  fun getRellenos() : Call<RellenoWrapper>

  @POST("orden/{id}")
  fun submitOrden(@Body params: Orden, @Path("id") id:Int) : Call<Orden>
}