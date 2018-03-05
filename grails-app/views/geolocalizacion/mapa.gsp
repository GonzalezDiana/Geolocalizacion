<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="geolicalizacion/mapa.css">
    <style>
    #map {
        height: 400px;
        width: 100%;
    }
    #table{
        width: 100%;
        border: 1px solid #000;
    }
    th, td {
        width: 25%;
        text-align: left;
        vertical-align: top;
        border: 1px solid #000;
        border-collapse: collapse;
        padding: 0.3em;
        caption-side: bottom;
    }
    caption {
        padding: 0.3em;
        color: #fff;
        background: #000;
    }
    th {
        background: #eee;
    }
    </style>
</head>
<body>

<div id="table"></div>
<table>
    <tr>
        <td><strong>ID</strong></td>
        <td><strong>DESCRIPCION</strong></td>
        <td><strong>DISTANCIA</strong></td>
        <td><strong>DIRECCION</strong></td>
    </tr>

    <g:each in="${this.agencias}" var="ag">
        <tr>
            <td>${ag.id}</td>
            <td>${ag.descripcion}</td>
            <td>${ag.distancia}</td>
            <td>${ag.direccion}</td>
        </tr>
    </g:each>
</table>

<br>
<h3>AGENCIAS MAS CERCANAS</h3>
<div id="map"></div>
<script>
    function initMap() {
        var uluru = {lat: ${this.latitud}, lng: ${this.longitud}};
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 4,
            center: uluru
        });
        <g:each in="${this.agencias}" var="a">
        var marker = new google.maps.Marker({
            position: {lat: ${a.latitud}, lng: ${a.longitud}},
            map: map
        });
        </g:each>
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAVanx2ePfDsWZumx52pDvlyDAS4E7VaDE&callback=initMap">
</script>
</body>
</html>

