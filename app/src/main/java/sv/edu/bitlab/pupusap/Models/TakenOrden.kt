package sv.edu.bitlab.pupusap.Models

import android.os.Parcel
import android.os.Parcelable
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import kotlin.collections.ArrayList

class TakenOrden() : Parcelable {
  var precioUnidad = 0.5f
  var textInput = ""
  var maiz = hashMapOf(
    QUESO to 0,
    FRIJOLES to 0,
    REVUELTAS to 0
  )
  var arroz = hashMapOf(
    QUESO to 0,
    FRIJOLES to 0,
    REVUELTAS to 0
  )
  private var fecha:DateTime = DateTime()

  override fun writeToParcel(dest: Parcel?, flags: Int) {
    dest!!.writeFloat(this.precioUnidad)
    dest.writeInt(maiz[QUESO]!!)
    dest.writeInt(maiz[FRIJOLES]!!)
    dest.writeInt(maiz[REVUELTAS]!!)
    dest.writeInt(arroz[QUESO]!!)
    dest.writeInt(arroz[FRIJOLES]!!)
    dest.writeInt(arroz[REVUELTAS]!!)
    dest.writeString(this.getFecha())
  }

  constructor(parcel: Parcel) : this() {
    precioUnidad = parcel.readFloat()
    maiz[QUESO] = parcel.readInt()
    maiz[FRIJOLES] = parcel.readInt()
    maiz[REVUELTAS] = parcel.readInt()
    arroz[QUESO] = parcel.readInt()
    arroz[FRIJOLES] = parcel.readInt()
    arroz[REVUELTAS] = parcel.readInt()
    setFecha(parcel.readString()!!)
  }

  override fun describeContents(): Int {
    return 0
  }

  fun getTotal(): Float {
    val totalMaiz = maiz.map { entry ->
      entry.value
    }.reduce { total, counter -> total+counter }

    val totalArroz = arroz.map { entry ->
      entry.value
    }.reduce { total, counter -> total+counter }

    return (totalArroz * precioUnidad) + (totalMaiz * precioUnidad)
  }

  fun getFecha(): String {
    val formatter = DateTimeFormat.forPattern(FORMATO_FECHA)
    return formatter.print(fecha)
  }

  fun setFecha(fecha: String) {
    val formatter = DateTimeFormat.forPattern(FORMATO_FECHA)
    this.fecha = formatter.parseDateTime(fecha)
  }


  companion object CREATOR : Parcelable.Creator<TakenOrden> {

    const val QUESO = "QUESO"
    const val FRIJOLES = "FRIJOLES"
    const val REVUELTAS = "REVUELTAS"
    const val MAIZ = "MAIZ"
    const val ARROZ = "ARROZ"
    const val FORMATO_FECHA = "dd-MM-yyyy" //09-09-2019

    override fun createFromParcel(parcel: Parcel): TakenOrden {
      return TakenOrden(parcel)
    }

    override fun newArray(size: Int): Array<TakenOrden?> {
      return arrayOfNulls(size)
    }


    fun randomOrders() :ArrayList<TakenOrden>{
      var lista = arrayListOf<TakenOrden>()
      for(index in 0..10){
        val orden = TakenOrden()
        val randomDaysNumbers = (Math.random() *10 ).toInt()
        var fechaRandom = DateTime()
        orden.fecha = if(randomDaysNumbers % 2 == 0) fechaRandom.plusDays(randomDaysNumbers) else fechaRandom.minusDays(randomDaysNumbers)

        orden.maiz[QUESO] = (Math.random() *10 ).toInt()
        orden.maiz[FRIJOLES] = (Math.random() *10 ).toInt()
        orden.maiz[REVUELTAS] = (Math.random() *10 ).toInt()
        orden.arroz[QUESO] = (Math.random() *10 ).toInt()
        orden.arroz[FRIJOLES] = (Math.random() *10 ).toInt()
        orden.arroz[REVUELTAS] = (Math.random() *10 ).toInt()
        lista.add(orden)
      }
      return lista
    }
  }

}