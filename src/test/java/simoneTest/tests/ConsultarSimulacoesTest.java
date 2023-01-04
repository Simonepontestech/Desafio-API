package allainTest.tests;

import allainTest.utils.Constants;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ConsultarSimulacoesTest extends Constants {

    // Retorna HTTP Status 204 se não existir simulações cadastradas
    // Retorna a lista de simulações cadastradas e existir uma ou mais
    @Test
    public void T01ConsultarSimulacoes(){
        given()
                .when()
                .get(SIMULACOES)
                .then()
                .log().all()
                .statusCode(200)
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("cpf"))
                .body("[0]", hasKey("nome"))
                .body("[0]", hasKey("email"))
                .body("[0]", hasKey("valor"))
                .body("[0]", hasKey("parcelas"))
                .body("[0]", hasKey("seguro"))
        ;
    }

    // Retorna HTTP Status 204 se não existir simulações cadastradas
    @Test
    public void T02ConsultarSimulacoesVazias(){
        given()
                .when()
                .get(SIMULACOES)
                .then()
                .log().all()
                .statusCode(204)

        ;
    }


}
