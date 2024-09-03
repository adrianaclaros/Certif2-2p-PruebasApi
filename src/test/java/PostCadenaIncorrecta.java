import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class PostCadenaIncorrecta {

    //TÍTULO: Verificar que PostBooking retorna error si hay una variable incorrecta en las claves de cadena
    //SEVERIDAD:  3
    //DESCRIPCIÓN: Enviar una solicitud POST donde el valor de las claves "firstname", "lastname" o "additionalneeds"
    //              no sea una cadena válida para confirmar que el sistema devuelve un error adecuado
    //SE ESPERA 400: «Mala petición». El servidor no puede devolver una respuesta debido a un error del cliente

    @Test
    public void RevisionCadenaIvalidaTest(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        String payload = """
                {
                "firstname" : 1584,
                "lastname" : "Brown",
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

        response.then().assertThat().statusCode(400);
        response.then().log().body();

    }
}

// RESULTADO: Falló. No pasó porque el resultado obtenido fue un 500 Internal Server Error
// en lugar del Status Code 400 Bad Request que era el esperado