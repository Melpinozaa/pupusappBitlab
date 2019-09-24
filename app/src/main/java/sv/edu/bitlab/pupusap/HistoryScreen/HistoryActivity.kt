package sv.edu.bitlab.pupusap.HistoryScreen

import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import sv.edu.bitlab.pupusap.Models.Orden
import sv.edu.bitlab.pupusap.OrdenDetalleFragment
import sv.edu.bitlab.pupusap.R
public const val VARIABLE = 2
class HistoryActivity : AppCompatActivity(),
  HistoryListFragment.HistoryListFragmentListener,
  OrdenDetalleFragment.OrdenDetalleFragmentListener {
  override fun onConfirmarOrden() {
    Toast.makeText(this, "CONFIRMANDO ORDEN DESDE HISTORIAL!!!", Toast.LENGTH_LONG).show()
  }

  override fun onReorder() {
    Toast.makeText(this, "CONFIRMANDO REORDENANDO DESDE HISTORIAL!!!", Toast.LENGTH_LONG).show()
  }

  private lateinit var ordenes: ArrayList<Orden>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    ordenes = if(savedInstanceState == null)
      Orden.randomOrders()
    else
      savedInstanceState.getParcelableArrayList(HISTORIAL_DE_ORDENES)!!

    if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
      setContentView(R.layout.activity_history_landscape)
      initLandsacpe()
    } else{
      setContentView(R.layout.activity_history_portrait)
      initPortrait()
    }
  }

  fun initLandsacpe(){
    loadListFragment(R.id.listContainer)
  }

  fun initPortrait(){
    loadListFragment(R.id.fragmentContainer)
  }

  fun loadListFragment(containerID: Int) {
    val fragment = HistoryListFragment.newInstance(ordenes)
    val builder = supportFragmentManager
      .beginTransaction()
      .replace(containerID, fragment, LIST_FRAGMENT_TAG)
      .addToBackStack(LIST_FRAGMENT_TAG)
    builder.commitAllowingStateLoss()
  }

  fun loadDetailFragment(containerID: Int, orden: Orden) {
    val fragment = OrdenDetalleFragment.newInstance(orden, true)
    val builder = supportFragmentManager
      .beginTransaction()
      .replace(containerID, fragment, DETAIL_FRAGMENT_TAG)
      .addToBackStack(DETAIL_FRAGMENT_TAG)
    builder.commitAllowingStateLoss()

  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putParcelableArrayList(HISTORIAL_DE_ORDENES, ordenes)
  }

  override fun onItemClicked(position: Int) {
    val orden = ordenes[position]
    if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
      loadDetailFragment(R.id.detailContainer, orden)
    } else {
      loadDetailFragment(R.id.fragmentContainer, orden)
    }
  }


  override fun onFragmentInteraction(uri: Uri) {
  }


  companion object{
    const val LIST_FRAGMENT_TAG = "ORDENES_HISTORY"
    const val DETAIL_FRAGMENT_TAG = "DETAIL_FRAGMENT_TAG"
    const val HISTORIAL_DE_ORDENES = "HISTORIAL_DE_ORDENES"
  }
}
