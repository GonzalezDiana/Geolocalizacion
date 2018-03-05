package geolocalizacion

class Agencia {
    String direccion
    String ciudad
    String pais
    /* Tuve que usar double porque no encontre otra forma de separar la localizacion */
    Double latitud
    Double longitud
    /* Datos para la Tabla donde muestra las agencias cercanas */
    String id
    String descripcion
    String distancia

    static constraints = {
        direccion blank: false
        ciudad blank: false
        pais blank: false
        latitud blank: false
        longitud blank: false
        id blank: false
        descripcion blank: false
        distancia blank: false
    }

    Agencia() {
    }
}
