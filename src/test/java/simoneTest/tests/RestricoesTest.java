package allainTest.tests;

import allainTest.utils.Constants;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;

@RunWith(DataProviderRunner.class)
public class RestricoesTest extends Constants {

    @Test
    @UseDataProvider("cpfComRestricao")
    public void deveConsultarCPFComRestricao(String cpfComRestricao) {
        given()
                .when()
                .get(RESTRICOES + cpfComRestricao)
                .then()
                .log().all()
                .statusCode(200)
                .body("mensagem", Matchers.is("O CPF " + cpfComRestricao + " possui restrição"))
        ;
    }

    @Test
    @UseDataProvider("cpfSemRestricao")
    public void deveConsultarUmCPFSemRestricao(String cpfSemRestricao) {
        given()
                .when()
                .get(RESTRICOES + cpfSemRestricao)
                .then()
                .log().all()
                .statusCode(204)
        ;
    }
}
