import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class GetSinId {

    // TÍTULO: Verificar que GetBooking retorna un mensaje de error cuando no se envía un id
    //SEVERIDAD:  2
    //DESCRIPCIÓN:  Este test verifica que cuando se llama a GET /Booking/ sin proporcionar algun {id},
    //              retorna status code 404
    //SE ESPERA 404: «No se encontró el recurso solicitado»

    @Test
    public void getEmployeedByIdTest(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        Response response = RestAssured
                .given().when().get("/booking"); // en employees esta poniendo un parametro id
        response.then().assertThat().statusCode(400);
        response.then().log().body();

    }
}

//RESULTADO: Falló. El sistema no devolvió un error 404 cuando falta un ID.
//          En lugar de eso, devolvió 200 ok y una lista de reservas