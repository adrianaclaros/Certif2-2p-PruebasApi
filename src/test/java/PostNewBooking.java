import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class PostNewBooking {

    // TITULO: Verificar que PostBooking crea un Booking nuevo
    // SEVERIDAD: 1
    // DESCRIPCION: Este test verifica que al realizar llamada a POST /Booking con sus datos válidos,
    //              un nuevo registro es creado y devuelve status code 200 con los datos del booking recién creado
    // SE ESPERA 200: «Todo está bien». Actúa exactamente como se espera

    @Test
    public void crearBookingTest() {
        // Configurar la base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // Crear el JSON del cuerpo de la solicitud
        String payload = """
        {
            "firstname" : "Jim",
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

        // Realizar la solicitud POST para crear un nuevo booking
        Response response = RestAssured
                .given().contentType(ContentType.JSON).body(payload)
                .when().post("/booking");

        // Verificar que el status code es 200
        response.then().assertThat().statusCode(200);

        // Verificar que la respuesta contiene los datos del booking creado
        response.then().assertThat().body("booking.firstname", Matchers.equalTo("Jim"));
        response.then().assertThat().body("booking.lastname", Matchers.equalTo("Brown"));
        response.then().assertThat().body("booking.totalprice", Matchers.equalTo(111));
        response.then().assertThat().body("booking.depositpaid", Matchers.equalTo(true));
        response.then().assertThat().body("booking.bookingdates.checkin", Matchers.equalTo("2018-01-01"));
        response.then().assertThat().body("booking.bookingdates.checkout", Matchers.equalTo("2019-01-01"));
        response.then().assertThat().body("booking.additionalneeds", Matchers.equalTo("Breakfast"));
        response.then().log().body();

    }

}

// RESULTADO: Pasó. Si devuelve status code 200 con los datos del booking
