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
    if(convertView == null){
      val itemView =  infalter.inflate(R.layout.list_row, null)
      val holder = ViewHolder(itemView.findViewById(R.id.totalTxt),
                              itemView.findViewById(R.id.fechaTxt),
                              itemView.findViewById(R.id.ordenarDenuevoBtn),
                              itemView.findViewById(R.id.itemContainer))
      itemView.tag = holder
      displayData(holder, ordenes[position], position)
      return itemView
    } else {
      val holder = convertView.tag as ViewHolder
      displayData(holder, ordenes[position], position)
      return convertView
    }
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

  private fun displayData(holder: ViewHolder, orden:Orden, position: Int) {
    val startTime = Calendar.getInstance()
    holder.totalTxt.text = "Total: ${orden.getTotal()}"
    holder.fechaTxt.text = orden.getFecha()
    holder.ordenarDenuevoBtn.setOnClickListener { listener.onOrdenarDenuevoClick(orden) }
    holder.itemContainer.setOnClickListener { listener.onItemClick(position) }
    val endTime = Calendar.getInstance()
    val diffInMilliseconds = endTime.time.time - startTime.time.time
    val diffInSeconds = diffInMilliseconds/1000
    Log.d("ORDEN_ADAPTER", "Time to render view => $diffInSeconds")

  }

  interface OrdenItemListener{
    fun onOrdenarDenuevoClick(orden: Orden)
    fun onItemClick(position: Int)
  }

  private class ViewHolder(var totalTxt: TextView,
                           var fechaTxt:TextView,
                           var ordenarDenuevoBtn:Button,
                           var itemContainer:View)
}