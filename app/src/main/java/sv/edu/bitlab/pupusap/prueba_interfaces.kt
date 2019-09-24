package sv.edu.bitlab.pupusap

class Carro(var color:String, var motor:String, var numeroPuertas:Int): Conductor.Vehiculo{
  override fun encender() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun manejar() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}
class Moto(var color:String, var motor:String) : Conductor.Vehiculo{
  override fun encender() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun manejar() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}

class Conductor(var vehiculo: Vehiculo){
  fun moverse(){
    vehiculo.encender()
    vehiculo.manejar()
  }

  interface Vehiculo{
    fun encender()
    fun manejar()
  }
}