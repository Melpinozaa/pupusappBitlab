package sv.edu.bitlab.pupusap

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sv.edu.bitlab.pupusap.Models.Orden
import sv.edu.bitlab.pupusap.Models.Relleno
import sv.edu.bitlab.pupusap.Models.TakenOrden
import sv.edu.bitlab.pupusap.Models.RellenoWrapper
import sv.edu.bitlab.pupusap.Network.ApiService

class MainActivity : AppCompatActivity(), RellenoViewHolder.RellenoViewHolderListener {

    val orden = TakenOrden()

    val pupusaStringResources = hashMapOf(
        QUESO to R.string.pupusa_queso,
        FRIJOLES to R.string.frijol_con_queso,
        REVUELTAS to R.string.revueltas
    )

    var botonesMaiz = hashMapOf<String, Button>()
    var botonesArroz = hashMapOf<String, Button>()
    var quesoIzquierda: Button? = null
    var frijolIzquierda: Button? = null
    var revueltaIzquierda: Button? = null

    var quesoDerecha: Button? = null
    var frijolDerecha: Button? = null
    var revueltasDerecha: Button? = null
    var loadingContainer: View? = null

    var sendButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val orden = Orden(0, arrayListOf(), arrayListOf(), 0.5f, 10.50f)
        val loadingContainer = findViewById<View>(R.id.loadingContainer)
        val rellenosList = findViewById<RecyclerView>(R.id.rellenosList)
        rellenosList.layoutManager = LinearLayoutManager(this)
        rellenosList.adapter = RellenosListAdapter(arrayListOf<Relleno>(), this)
        loadingContainer.visibility = View.VISIBLE

        ApiService.create().getRellenos().enqueue(object : Callback<RellenoWrapper>{
            override fun onFailure(call: Call<RellenoWrapper>, t: Throwable) {
                loadingContainer.visibility = View.GONE
                AlertDialog.Builder(getContent())
                    .setTitle("ERROR")
                    .setMessage("Error con el servidor lo sentimos")
                    .setNeutralButton("ok", null)
                    .create()
                    .show()
            }

            override fun onResponse(
                call: Call<RellenoWrapper>,
                response: Response<RellenoWrapper>
            ) {
                loadingContainer.visibility = View.GONE
                val rellenos = response.body()!!.rellenos
                val adapter = rellenosList.adapter as RellenosListAdapter
                adapter.rellenos = rellenos
                adapter.notifyDataSetChanged()
            }
        })
    }


    fun getContent(): Context {
        return this
    }




    fun displayCounters() {
        for ((key,value) in orden.maiz){
            val resource = pupusaStringResources[key]
            val text = this.resources.getString(resource!!, value)
            botonesMaiz[key]!!.text = text
        }


        for ((key,value) in orden.arroz){
            val resource = pupusaStringResources[key]
            val text = this.resources.getString(resource!!, value)
            botonesArroz[key]!!.text = text
        }

    }

    fun addMaiz(relleno: String) {
        orden.maiz[relleno] = orden.maiz[relleno]!! + 1
        val contador = orden.maiz[relleno]
        val resource = pupusaStringResources[relleno]
        val text = this.resources.getString(resource!!, contador)
        botonesMaiz[relleno]!!.text = text
    }
    fun addArroz(relleno: String) {
        orden.arroz[relleno] = orden.arroz[relleno]!! + 1
        val contador =  orden.arroz[relleno]
        val resource = pupusaStringResources[relleno]
        val text = this.resources.getString(resource!!, contador)
        botonesArroz[relleno]!!.text = text
    }

    private fun confirmarOrden() {
        val intent = Intent(this, DetalleOrdeActivity::class.java)
        intent.putExtra(ORDEN,orden)
        this.startActivity(intent)
    }

    fun showLoading(show: Boolean) {
        val visibility = if(show) View.VISIBLE else View.GONE
        loadingContainer!!.visibility = visibility
    }


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

    }

    companion object{
        const val MAIZ = "MAIZ"
        const val ARROZ = "ARROZ"
    }

}
