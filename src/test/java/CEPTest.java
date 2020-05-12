import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.json.Json;

public class CEPTest {

    @BeforeClass
    public static void setUp() {
    }

    @AfterClass
    public static void tearDownTest() {
    }

    @Test
    public void ConsultaCEPvalido() {
        given().when().get("http://viacep.com.br/ws/91060900/json/").then().statusCode(200)
                .body("cep", containsString("91060-900"))
                .body("logradouro",containsString("Avenida Assis Brasil 3940"))
                .body("Complemento",equalTo(null))
                .body("bairro",containsString("São Sebastião"))
                .body("localidade",containsString("Porto Alegre"))
                .body("uf",containsString("RS"))
                .body("ibge",containsString("4314902"));

    }
    @Test
    public void ConsultaCEPInexistente() {

        given().when().get("http://viacep.com.br/ws/91060910/json/").then().statusCode(200)
                .body("erro", equalTo(true));
    }

    @Test
    public void ConsultaCEPFormatoInvalido() {

        given().when().get("http://viacep.com.br/ws/0000000/json/").then().statusCode(400);

    }
    }
