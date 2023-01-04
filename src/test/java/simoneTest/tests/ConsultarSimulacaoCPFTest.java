package allainTest.tests;

import allainTest.utils.Constants;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class ConsultarSimulacaoCPFTest extends Constants {

    // Retorna a simulação cadastrada
    // Se o CPF não possuir uma simulação o HTTP Status 404 é retornado

    @Test
    public void T01ConsultarSimulacaoCPF(){
        // Estou utilizando o valor já cadastrado no banco, para eu não depender da criação de uma simulação
        String cpf = "17822386034";
        given()
                .when()
                .get(SIMULACOES + cpf)
                .then()
                .log().all()
                .statusCode(200)
                .body("$", hasKey("id"))
                .body("$", hasKey("cpf"))
                .body("$", hasKey("nome"))
                .body("$", hasKey("email"))
                .body("$", hasKey("valor"))
                .body("$", hasKey("parcelas"))
                .body("$", hasKey("seguro"))
        ;
    }

    @Test
    public void T02ConsultarSimulacaoCPFInvalido(){
        String cpf = "79865432102";
        given()
                .when()
                .get(SIMULACOES + cpf)
                .then()
                .log().all()
                .statusCode(404)
                .body("mensagem", is("CPF " + cpf + " não encontrado"))
        ;
    }
}
