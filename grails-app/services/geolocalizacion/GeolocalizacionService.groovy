package geolocalizacion

import groovy.json.JsonSlurper


class GeolocalizacionService {

    /*
    * Obtiene la localizacion de una direccion sacando el result del json
    * */
    def getLocalizacion(String address) {
        /* Obtengo la key de la pagina */
        def url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=${address}&key=AIzaSyAVanx2ePfDsWZumx52pDvlyDAS4E7VaDE")
        def connection = (HttpURLConnection) url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozzilla/5.0")
        JsonSlurper json = new JsonSlurper()
        def result = json.parse(connection.getInputStream())
        //result.toString()
        return result
    }

    /*
    * Obtengo de la localizacion completa solo la latitud
    * */
    def getLatitud(result){
        //println result.results.first().geometry.location.lat
        return result.results.first().geometry.location.lat
    }

    /*
    * Obtengo de la localizacion completa solo la longitud
    * */
    def getLongitud(result){
        //println result.results.first().geometry.location.lng
        return result.results.first().geometry.location.lng
    }

    /*
    * Obtiene las (maximo 10) agencias mas cercanas filtrando por medio de pago, latitud, longitud y radio
    * */
    def getAgencies(String medio_pago, localizacion, int radio) {
        def url = new URL("https://api.mercadolibre.com/sites/MLA/payment_methods/${medio_pago}/agencies?near_to=${getLatitud(localizacion)},${getLongitud(localizacion)},${radio}&limit=10")
        def connection = (HttpURLConnection) url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozzilla/5.0")
        JsonSlurper json = new JsonSlurper()
        def result = json.parse(connection.getInputStream())
        /* creo una lista de agencias a partir del json */
        ArrayList<Agencia> agencias = new ArrayList<>();
        result.results.each { jsonAgency ->
            agencias.add(jsonToAgency(jsonAgency))
        }
        return agencias
    }

    /*
    * Recibo el json con las categorias y filtro los datos para almacenarlos en Agencias
    * */
    Agencia jsonToAgency(def json) {
        //println "Separacion ${json.address.location}"
        def location = json.address.location.split(",")
        String latitud = location[0]
        //println "Latitud: "+latitud
        String longitud = location[1]
        //println "Longitud: "+longitud

        Agencia agencia = new Agencia();
        agencia.direccion = json.address.address_line
        //println agencia.direccion
        agencia.ciudad = json.address.city
        agencia.pais = json.address.country
        agencia.latitud = latitud.replace(',','.') as Double
        //println agencia.latitud
        agencia.longitud = longitud.replace(',','.') as Double
        //println agencia.longitud
        agencia.id = json.agency_code
        agencia.descripcion = json.description
        agencia.distancia = json.distance
        return agencia
    }
}
