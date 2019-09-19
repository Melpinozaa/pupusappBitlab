package sv.edu.bitlab.pupusap.HistoryScreen.HistoryRecyclerView

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sv.edu.bitlab.pupusap.Models.Orden
import sv.edu.bitlab.pupusap.R

class HistoryItemViewHolder(itemView : View, val listener:OrdenItemListener) : RecyclerView.ViewHolder(itemView) {

  var fechaTxt: TextView? = null
  var totalTxt: TextView? = null
  var ordenarDenuevoBtn: Button? = null
  var contenedor:View? = null
  var editText:EditText? = null


  fun bindData(orden: Orden) {

    fechaTxt = itemView.findViewById(R.id.fechaTxt)
    totalTxt = itemView.findViewById(R.id.totalTxt)
    ordenarDenuevoBtn = itemView.findViewById(R.id.ordenarDenuevoBtn)
    contenedor = itemView.findViewById(R.id.itemContainer)
    editText = itemView.findViewById(R.id.input)
    totalTxt!!.text = orden.textInput
    fechaTxt!!.text = orden.getFecha()
    ordenarDenuevoBtn!!.setOnClickListener {
      listener.onTextInput(editText!!.text.toString(), this.adapterPosition)
    }
    contenedor!!.setOnClickListener { Log.d("RECYCLER_VIEW", "Click en contenedor") }

  }


  interface OrdenItemListener{
    fun onOrdenarDenuevoClick(orden: Orden)
    fun onItemClick(position: Int)
    fun onTextInput(input:String, position: Int)
  }
}