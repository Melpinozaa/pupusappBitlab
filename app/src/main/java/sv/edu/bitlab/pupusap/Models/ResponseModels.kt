package sv.edu.bitlab.pupusap.Models

data class RellenoWrapper(var rellenos: List<Relleno>)
data class Relleno(var nombre: String, var id: Int)
data class OrdenPupusas(var relleno: Relleno, var total: Int)
data class Orden(var id: Int,
                 var arroz:List<OrdenPupusas>,
                 var maiz: List<OrdenPupusas>,
                 var precio_unidad: Float,
                 var total: Float)

