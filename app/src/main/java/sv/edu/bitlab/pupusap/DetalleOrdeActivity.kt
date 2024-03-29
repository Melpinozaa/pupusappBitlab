package sv.edu.bitlab.pupusap

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import sv.edu.bitlab.pupusap.Models.TakenOrden

class DetalleOrdeActivity : AppCompatActivity(), OrdenDetalleFragment.OrdenDetalleFragmentListener {
  override fun onConfirmarOrden() {
    Toast.makeText(this, "CONFIRMANDO ORDEN!!!", Toast.LENGTH_LONG).show()
  }
  var orden = TakenOrden()


  override fun onReorder() {
    Toast.makeText(this, "REORDENANDO PUPUSAS!!!", Toast.LENGTH_LONG).show()
  }

  var orden = Orden()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detalle_orden)
    Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show()
    val params = this.intent.extras
    orden = params!!.getParcelable<TakenOrden>(ORDEN)!!
    Log.d("ACTIVITY", "onCreate()")
    addFragment()
  }

  fun addFragment() {
    val fragment = OrdenDetalleFragment.newInstance(this.orden)
    val builder = supportFragmentManager
      .beginTransaction()
      .add(R.id.pruebaFragmentContainer, fragment, FRAGMENT_TAG)
    builder.commit()
  }


  //region DetalleOrdeActivity
  override fun onFragmentInteraction(uri: Uri) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
  //endregion

  companion object{
    const val FRAGMENT_TAG = "LIST_FRAGMENT_TAG"
  }
}
