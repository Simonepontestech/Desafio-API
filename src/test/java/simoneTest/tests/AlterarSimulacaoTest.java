package allainTest.tests;

import allainTest.utils.Constants;
import allainTest.utils.JSON;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlterarSimulacaoTest extends Constants {
    String cpf;

    @Test
    public void T01criandoSimulacao(){
        criarSimulacao().testeComSucesso(NOME, CPF2, EMAIL, VALOR, PARCELAS, SEGURO);
    }

    @Test
    public void T02criandoSimulacaoVazia(){
        criarSimulacao().testeComErro(null, null, null, null, null, null);
    }

    @Test
    public void T03NomeVazio(){
        String nome = null;
        criarSimulacao().testeComErro(nome,cpf,EMAIL,VALOR,PARCELAS,SEGURO);
    }



    //-------------------------------------CPF-------------------------------------------//
    @Test
    public void T04CPFRepetido(){
        String json = new JSON().passarChaves(NOME, CPF2, EMAIL, VALOR, PARCELAS, SEGURO);
        criarSimulacao().testeComErro(NOME,CPF2,EMAIL,VALOR,PARCELAS,SEGURO);
    }

    @Test
    public void T05CPFVazio(){
        String cpf = null;
        criarSimulacao().testeComErro(NOME,cpf,EMAIL,VALOR,PARCELAS,SEGURO);
    }

    @Test
    public void T06CPFComLetras(){
        String cpf = sortearCPFComLetra();
        criarSimulacao().testeComErro(NOME,cpf,EMAIL,VALOR,PARCELAS,SEGURO);
    }

    @Test
    public void T07CPFComMascara(){
        //analisar a mascara
        String cpf = sortearCPFComMascara();
        criarSimulacao().testeComErro(NOME,cpf,EMAIL,VALOR,PARCELAS,SEGURO);
    }

    @Test
    public void T08CPFComMaisDigitos(){
        String cpf = sortearCPFComMaisDigitos();
        criarSimulacao().testeComErro(NOME,cpf,EMAIL,VALOR,PARCELAS,SEGURO);
    }

    @Test
    public void T09CPFComMenosDigitos(){
        String cpf = sortearCPFComMenosDigitos();
        criarSimulacao().testeComErro(NOME,cpf,EMAIL,VALOR,PARCELAS,SEGURO);
    }


    //-------------------------------------Emails-------------------------------------------//
    @Test
    public void T10EmailInvalido(){
        String email = "allain.10.com";
        criarSimulacao().testeComErro(NOME,cpf,email,VALOR,PARCELAS,SEGURO);
    }

    @Test
    public void T11EmailInvalido2(){
        String email = "test";
        criarSimulacao().testeComErro(NOME,cpf,email,VALOR,PARCELAS,SEGURO);
    }

    @Test
    public void T12EmailVazio(){
        String email = null;
        criarSimulacao().testeComErro(NOME,cpf,email,VALOR,PARCELAS,SEGURO);
    }

    //-------------------------------------Valores-------------------------------------------//
    @Test
    public void T13ValorVazio(){
        Float valor = null;
        criarSimulacao().testeComErro(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO);

    }

    @Test
    public void T14ValorIgual1000(){
        Float valor = 1000F;
        criarSimulacao().testeComSucesso(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO);
    }

    @Test
    public void T15ValorMaiorQue1000(){
        Float valor = 35000F;
        criarSimulacao().testeComSucesso(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO);
    }

    @Test
    public void T16ValorMenorQue1000(){
        Float valor = 900F;
        criarSimulacao().testeComErro(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO);
    }

    @Test
    public void T17ValorNegativo(){
        Float valor = -100F;
        criarSimulacao().testeComErro(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO);
    }

    @Test
    public void T18ValorMaiorQue40000(){
        Float valor = 50000F;
        criarSimulacao().testeComErro(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO);
    }

    @Test
    public void T19ValorIgual40000(){
        Float valor = 40000F;
        criarSimulacao().testeComSucesso(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO);
    }

    //-------------------------------------parcelas-------------------------------------------//
    @Test
    public void T20ParcelaVazia(){
        Integer parcelas = null;
        criarSimulacao().testeComErro(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO);
    }

    @Test
    public void T21ParcelasIgual2(){
        Integer parcelas = 2;
        criarSimulacao().testeComSucesso(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO);
    }

    @Test
    public void T22ParcelasMenor2(){
        Integer parcelas = 1;
        criarSimulacao().testeComSucesso(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO);
    }

    @Test
    public void T23ParcelasMaior2(){
        Integer parcelas = 5;
        criarSimulacao().testeComSucesso(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO)
                .body("parcelas", is(5));
    }

    @Test
    public void T24ParcelasMenor48(){
        Integer parcelas = 47;
        criarSimulacao().testeComSucesso(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO);
    }

    @Test
    public void T25ParcelasIgual48(){
        Integer parcelas = 48;
        criarSimulacao().testeComSucesso(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO);
    }

    @Test
    public void T26ParcelasMaior48(){
        Integer parcelas = 50;
        criarSimulacao().testeComErro(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO);
    }

    //-------------------------------------seguro-------------------------------------------//
    @Test
    public void T27SeguroVazio(){
        Boolean seguro = null;
        criarSimulacao().testeComErro(NOME,cpf,EMAIL,VALOR,PARCELAS,seguro);
    }

    @Test
    public void T28SeguroTrue(){
        Boolean seguro = true;
        criarSimulacao().testeComSucesso(NOME,cpf,EMAIL,VALOR,PARCELAS,seguro);
    }

    @Test
    public void T29SeguroFalse(){
        String cpf = sortearCPF();
        criarSimulacao().testeComSucesso(NOME,cpf,EMAIL,VALOR,PARCELAS,SEGURO);
    }


    //-------------------------------------------------------------------------------------------------------------------------------//

    private ValidatableResponse testeComSucesso(String nome, String cpf, String email, Float valor, Integer parcelas, Boolean seguro){
        String json = new JSON().passarChaves(nome, cpf, email, valor, parcelas, seguro);
        return criarRequisicao(json, 200)
                .body("nome", is(nome))
                .body("cpf", is(cpf))
                .body("email", is(email))
                .body("valor", is(valor))
                .body("parcelas", is(parcelas))
                .body("seguro", is(seguro))
                ;

    }

    private ValidatableResponse testeComErro(String nome, String cpf, String email, Float valor, Integer parcelas, Boolean seguro) {
        String json = new JSON().passarChaves(nome, cpf, email, valor, parcelas, seguro);
        return criarRequisicao(json, 404)
                .body(containsString("erros"));
    }

    private AlterarSimulacaoTest criarSimulacao() {
        cpf = sortearCPF();
        String json = new JSON().passarChaves(NOME, cpf, EMAIL, VALOR, PARCELAS, SEGURO);
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(SIMULACOES)
        ;
        return this;
    }

    private ValidatableResponse criarRequisicao(String json, int code){
        return
                given()
                        .contentType(ContentType.JSON)
                        .body(json)
                        .when()
                        .put(SIMULACOES + cpf)
                        .then()
                        .log().all()
                        .statusCode(code)
                ;
    }
}
