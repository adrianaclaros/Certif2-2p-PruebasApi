import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class GetConIdInvalido {

    //TÍTULO: Verificar que GetBooking retorna un mensaje de error cuando el id enviado es de un formato inválido
    //SEVERIDAD:  2
    //DESCRIPCIÓN: Este test verifica que cuando se llama a GET /Booking/{id} con un ID en un formato inválido,
    //              el en este caso id:"BFDS" en lugar de id:1, el sistema debe retornar 400 Bad Request
    // SE ESPERA 400: «Mala petición». El servidor no puede devolver una respuesta debido a un error del cliente

    @Test
    public void getEmployeedByIdTest(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        Response response = RestAssured
                .given().pathParams("id", "BFDS") //como parametro en el url, el id será 1
                .when().get("/booking/{id}"); // en employees esta poniendo un parametro id
        response.then().assertThat().statusCode(400);
        response.then().log().body();


    }
}

// Resultado: Falló. El sistema devuelve 404 Not Found para un ID inválido, pero,
//             se esperaba un 400 Bad Request por que es una solicitud malformada por parte del cliente
