package sv.edu.bitlab.pupusap.HistoryScreen

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import sv.edu.bitlab.pupusap.Models.Orden
import sv.edu.bitlab.pupusap.R
import java.util.*
import kotlin.collections.ArrayList

class OrdenAdapter(var ordenes: ArrayList<Orden>,
                   val infalter: LayoutInflater,
                   val listener: OrdenItemListener) : BaseAdapter() {

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val itemView =  if(convertView == null){
      infalter.inflate(R.layout.list_row, null)
    } else {
      convertView
    }
    displayData(itemView, ordenes[position], position)
    return itemView
  }

  override fun getItem(position: Int): Any {
    return ordenes[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getCount(): Int {
    return ordenes.size
  }

  fun displayData(itemView: View, orden:Orden, position: Int): Unit {
    val startTime = Calendar.getInstance()
    itemView.findViewById<TextView>(R.id.totalTxt).text = "Total: ${orden.getTotal()}"
    itemView.findViewById<TextView>(R.id.fechaTxt).text = orden.getFecha()
    itemView.findViewById<Button>(R.id.ordenarDenuevoBtn).setOnClickListener { listener.onOrdenarDenuevoClick(orden) }
    //itemView.findViewById<View>(R.id.itemContainer).setOnClickListener { listener.onItemClick(position) }
    val endTime = Calendar.getInstance()
    val diffInMilliseconds = endTime.time.time - startTime.time.time
    val diffInSeconds = diffInMilliseconds
    Log.d("ORDEN_ADAPTER", "Time to render view => $diffInSeconds")

  }

  interface OrdenItemListener{
    fun onOrdenarDenuevoClick(orden: Orden)
    fun onItemClick(position: Int)
  }
}