package allainTest.tests;

import allainTest.utils.Constants;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RemoverSimulacaoTest extends Constants {

    // Retorna o HTTP Status 204 se simulação for removida com sucesso
    @Test
    public void T01RemoverSimulacaoPorID(){
        Integer id = 12;
        given()
                .when()
                .delete(SIMULACOES + id)
                .then()
                .log().all()
                .statusCode(204)
                .body(is("OK"))
        ;
    }

    //Retorna o HTTP Status 404 com a mensagem "Simulação não encontrada" se não
    //existir a simulação pelo ID informado
    @Test
    public void T02RemoverSimulacaoPorIDInexistente(){
        Integer id = 1001;
        given()
                .when()
                .delete(SIMULACOES + id)
                .then()
                .log().all()
                .statusCode(404)
                .body("mensagem",is("Simulação não encontrada"))
        ;
    }
}
