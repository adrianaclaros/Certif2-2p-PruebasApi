import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class PostConCamposFaltantes {

    //TÍTULO: Verificar que PostBooking retorna un error cuando falta un campo obligatorio en el JSON
    //SEVERIDAD:  2
    //DESCRIPCIÓN:  Enviar una solicitud POST sin incluir uno o más campos obligatorios, como "nombre" y "apellido",
    //              para verificar que el sistema devuelve un error adecuado
    // SE ESPERA 400: «Mala petición». El servidor no puede devolver una respuesta por un error del cliente

    @Test
    public void CampoObligatorioIncompletoTest() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        String payload = """
    {
        "totalprice" : 111,
        "depositpaid" : true,
        "bookingdates" : {
            "checkin" : "2018-01-01",
            "checkout" : "2019-01-01"
        },
        "additionalneeds" : "Breakfast"
    }
    """;

        Response response = RestAssured
                .given().contentType(ContentType.JSON).body(payload)
                .when().post("/booking");

        response.then().assertThat().statusCode(400); // Suponiendo que el error devuelve un 400
        response.then().log().body();
    }

}

// RESULTADO: Falló. El resultado obtenido fue un 500 Internal Server Error
//              en lugar del Status Code 400 Bad Request que era el esperado