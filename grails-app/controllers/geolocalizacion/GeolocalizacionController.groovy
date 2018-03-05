package geolocalizacion

import grails.converters.JSON
import groovy.json.JsonSlurper

class GeolocalizacionController {

    def geolocalizacionService

    def index() {
        //println("INDEX")
        def url = new URL('https://api.mercadolibre.com/sites/MLA/payment_methods')
        def connection = (HttpURLConnection)url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozzilla/5.0")
        JsonSlurper json = new JsonSlurper()
        [medios: json.parse(connection.getInputStream())]
    }

    def showAgencias() {
        println("SELECCION")
        String direccion = params.address
        direccion = direccion.replaceAll(" ","+")
        println("Direccion: "+direccion)
        String medio_pago = params.pagoCon
        println("Medio de pago: "+medio_pago)
        Integer radio = params.radio.toInteger();
        println("Radio: "+radio)
        /* Obtiene la localizacion de una direccion sacando el result del json */
        def localizacion = geolocalizacionService.getLocalizacion(direccion)
        /* Obtiene las agencias mas cercanas filtrando por medio de pago, localizacion y radio */
        def agencias = geolocalizacionService.getAgencies(medio_pago, localizacion, radio)
        /* El mapa no me esta centrando bien la imagen, hay que hacer zoom */
        render(view:'mapa',model:[agencias:agencias, latitud:geolocalizacionService.getLatitud(localizacion), longitud:geolocalizacionService.getLatitud(localizacion) ])
    }
}
