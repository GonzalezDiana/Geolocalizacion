<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Geolocalizacion</title>
    <g:javascript library="jquery"/>
</head>

<body>
    <form method="get" action="showAgencias">
        Direccion: <input type="text" placeholder="Direccion" name="address"><br><br>
        Medios de pago:
        <select id=medio-select name="pagoCon">
            <g:each in="${medios}" var="m">
                <g:if test="${m.payment_type_id == "ticket"}">
                <option value="${m?.id}"> ${m?.name}</option>
                </g:if>
            </g:each>
        </select><br><br>
        Radio: <input type="number" placeholder="Radio en metros" name="radio"><br><br>
        <input type="submit" value="Consultar">
    </form>
</body>
</html>