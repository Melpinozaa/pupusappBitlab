package sv.edu.bitlab.pupusap.HistoryScreen.HistoryRecyclerView

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sv.edu.bitlab.pupusap.Models.TakenOrden
import sv.edu.bitlab.pupusap.R

class HistoryItemViewHolder(itemView : View, val listener:OrdenItemListener) : RecyclerView.ViewHolder(itemView) {

  var fechaTxt: TextView? = null
  var totalTxt: TextView? = null
  var verDetalleBtn:Button? = null
  var contenedor:View? = null


  fun bindData(orden: TakenOrden) {

    fechaTxt = itemView.findViewById(R.id.fechaTxt)
    totalTxt = itemView.findViewById(R.id.totalTxt)
    verDetalleBtn = itemView.findViewById(R.id.verDetalleBtn)
    contenedor = itemView.findViewById(R.id.itemContainer)
    totalTxt!!.text = "Total de la orden: ${orden.getTotal()}"
    fechaTxt!!.text = orden.getFecha()
    verDetalleBtn!!.setOnClickListener { listener.onItemClick(this.adapterPosition) }
    contenedor!!.setOnClickListener { Log.d("RECYCLER_VIEW", "Click en contenedor") }

  }


  interface OrdenItemListener{
    fun onOrdenarDenuevoClick(position: Int)
    fun onItemClick(position: Int)
    fun onTextInput(input:String, position: Int)
  }
}