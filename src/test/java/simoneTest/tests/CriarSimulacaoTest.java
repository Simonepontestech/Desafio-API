package allainTest.tests;

import allainTest.utils.Constants;
import allainTest.utils.JSON;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CriarSimulacaoTest extends Constants {

    //Uma simulação cadastrada com sucesso retorna o HTTP Status 201 e os dados inseridos como retorno
    //Uma simulação com problema em alguma regra retorna o HTTP Status 400 com a listade erros
    //Uma simulação para um mesmo CPF retorna um HTTP Status 409 com a mensagem "CPF já existente"

    @Test
    public void T01criandoSimulacao(){
        String nome = NOME;
        String cpf = CPF;
        String email = EMAIL ;
        Float valor = VALOR;
        Integer parcelas = PARCELAS;
        Boolean seguro = SEGURO;
        testeComSucesso(nome, cpf, email, valor, parcelas, seguro);
    }

    @Test
    public void T02criandoSimulacaoVazia(){
        String nome = null;
        String cpf = null;
        String email = null;
        Float valor = null;
        Integer parcelas = null;
        Boolean seguro = null;
        testeComErro(nome, cpf, email, valor, parcelas, seguro)
                .body("erros.parcelas", is( "Parcelas não pode ser vazio"))
                .body("erros.valor", is( "Valor não pode ser vazio"))
                .body("erros.cpf", is( "CPF não pode ser vazio"))
                .body("erros.nome", is( "Nome não pode ser vazio"))
                .body("erros.email", is( "E-mail não deve ser vazio"))
        ;
    }

    @Test
    public void T03NomeVazio(){
        String nome = null;
        String cpf = sortearCPF();
        testeComErro(nome,cpf,EMAIL,VALOR,PARCELAS,SEGURO)
                .body("erros.nome", is("Nome não pode ser vazio"));
    }



    //-------------------------------------CPF-------------------------------------------//
    @Test
    public void T04CPFRepetido(){
        String cpf = CPF;
        String json = new JSON().passarChaves(NOME, cpf, EMAIL, VALOR, PARCELAS, SEGURO);
        criarRequisicao(json, 409)
                .body("mensagem", is("CPF já existente"));
    }

    @Test
    public void T05CPFVazio(){
        String cpf = null;
        testeComErro(NOME,cpf,EMAIL,VALOR,PARCELAS,SEGURO)
                .body("erros.cpf", is("CPF não pode ser vazio"));
    }

    @Test
    public void T06CPFComLetras(){
        String cpf = sortearCPFComLetra();
        testeComErro(NOME,cpf,EMAIL,VALOR,PARCELAS,SEGURO);
    }

    @Test
    public void T07CPFComMascara(){
        //analisar a mascara
        String cpf = sortearCPFComMascara();
        testeComErro(NOME,cpf,EMAIL,VALOR,PARCELAS,SEGURO);
    }

    @Test
    public void T08CPFComMaisDigitos(){
        String cpf = sortearCPFComMaisDigitos();
        testeComErro(NOME,cpf,EMAIL,VALOR,PARCELAS,SEGURO);
    }

    @Test
    public void T09CPFComMenosDigitos(){
        String cpf = sortearCPFComMenosDigitos();
        testeComErro(NOME,cpf,EMAIL,VALOR,PARCELAS,SEGURO);
    }


    //-------------------------------------Emails-------------------------------------------//
    @Test
    public void T10EmailInvalido(){
        String email = "allain.10.com";
        String cpf = sortearCPF();
        testeComErro(NOME,cpf,email,VALOR,PARCELAS,SEGURO)
                .body("erros.email", is("E-mail deve ser um e-mail válido"));
    }

    @Test
    public void T11EmailInvalido2(){
        String email = "testeEmail";
        String cpf = sortearCPF();
        testeComErro(NOME,cpf,email,VALOR,PARCELAS,SEGURO)
                .body("erros.email", is("E-mail deve ser um e-mail válido"));
    }

    @Test
    public void T12EmailVazio(){
        String email = null;
        String cpf = sortearCPF();
        testeComErro(NOME,cpf,email,VALOR,PARCELAS,SEGURO)
                .body("erros.email", is("E-mail não deve ser vazio"));
    }

    //-------------------------------------Valores-------------------------------------------//
    @Test
    public void T13ValorVazio(){
        Float valor = null;
        String cpf = sortearCPF();
        testeComErro(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO)
                .body("erros.valor", is("Valor não pode ser vazio"));

    }

    @Test
    public void T14ValorIgual1000(){
        Float valor = 1000F;
        String cpf = sortearCPF();
        testeComSucesso(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO)
                .body("valor", is(1000F));
    }

    @Test
    public void T15ValorMaiorQue1000(){
        Float valor = 35000F;
        String cpf = sortearCPF();
        testeComSucesso(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO)
                .body("valor", is(35000F));
    }

    @Test
    public void T16ValorMenorQue1000(){
        Float valor = 900F;
        String cpf = sortearCPF();
        testeComErro(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO);
    }

    @Test
    public void T17ValorNegativo(){
        Float valor = -100F;
        String cpf = sortearCPF();
        testeComErro(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO);
    }

    @Test
    public void T18MaiorQue40000(){
        Float valor = 50000F;
        String cpf = sortearCPF();
        testeComErro(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO)
                .body("erros.valor", is("Valor deve ser menor ou igual a R$ 40.000"));
    }

    @Test
    public void T19ValorIgual40000(){
        Float valor = 40000F;
        String cpf = sortearCPF();
        testeComSucesso(NOME,cpf,EMAIL,valor,PARCELAS,SEGURO);
    }

    //-------------------------------------parcelas-------------------------------------------//
    @Test
    public void T20ParcelaVazia(){
        Integer parcelas = null;
        String cpf = sortearCPF();
        testeComErro(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO)
                .body("erros.parcelas", is("Parcelas não pode ser vazio"));
    }

    @Test
    public void T21ParcelasIgual2(){
        Integer parcelas = 2;
        String cpf = sortearCPF();
        testeComSucesso(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO)
                .body("parcelas", is(2));
    }

    @Test
    public void T22ParcelasMenor2(){
        Integer parcelas = 1;
        String cpf = sortearCPF();
        testeComErro(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO);
    }

    @Test
    public void T23ParcelasNegativa(){
        Integer parcelas = -1;
        String cpf = sortearCPF();
        testeComErro(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO)
                .body("erros.parcelas", is("Parcelas deve ser igual ou maior que 2"));
    }

    @Test
    public void T24ParcelasMaior2(){
        Integer parcelas = 5;
        String cpf = sortearCPF();
        testeComSucesso(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO)
                .body("parcelas", is(5));
    }

    @Test
    public void T25ParcelasMenor48(){
        Integer parcelas = 47;
        String cpf = sortearCPF();
        testeComSucesso(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO)
                .body("parcelas", is(47));
    }

    @Test
    public void T26ParcelasIgual48(){
        Integer parcelas = 48;
        String cpf = sortearCPF();
        testeComSucesso(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO)
                .body("parcelas", is(48));
    }

    @Test
    public void T27ParcelasMaior48(){
        Integer parcelas = 50;
        String cpf = sortearCPF();
        testeComErro(NOME,cpf,EMAIL,VALOR,parcelas,SEGURO);
    }

    //-------------------------------------seguro-------------------------------------------//
    @Test
    public void T28SeguroVazio(){
        Boolean seguro = null;
        String cpf = sortearCPF();
        testeComErro(NOME,cpf,EMAIL,VALOR,PARCELAS,seguro);
    }

    @Test
    public void T29SeguroTrue(){
        Boolean seguro = true;
        String cpf = sortearCPF();
        testeComSucesso(NOME,cpf,EMAIL,VALOR,PARCELAS,seguro)
                .body("seguro", is(true));
    }

    @Test
    public void T30SeguroInvalido(){
        String cpf = sortearCPF();
        String json = new JSON().passarChavesSeguro(NOME, cpf, EMAIL, VALOR, PARCELAS, "test");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(json)
                .log().all()
                .when()
                .post(SIMULACOES)
                .then()
                .log().all()
                .statusCode(400)
        ;
    }

    @Test
    public void T31SeguroFalse(){
        String cpf = sortearCPF();
        testeComSucesso(NOME,cpf,EMAIL,VALOR,PARCELAS,SEGURO)
                .body("seguro", is(false));
    }

    private ValidatableResponse testeComSucesso(String nome, String cpf, String email, Float valor, Integer parcelas, Boolean seguro){
        String json = new JSON().passarChaves(nome, cpf, email, valor, parcelas, seguro);
        return criarRequisicao(json, 201)
                .body("nome", is(nome))
                .body("cpf", is(cpf))
                .body("email", is(email))
                .body("valor", is(valor))
                .body("parcelas", is(parcelas))
                .body("seguro", is(seguro))
                ;
    }

    private ValidatableResponse testeComErro(String nome, String cpf, String email, Float valor, Integer parcelas, Boolean seguro){
        String json = new JSON().passarChaves(nome, cpf, email, valor, parcelas, seguro);
        return criarRequisicao(json, 400)
                .body(containsString("erros"));
    }

    private ValidatableResponse criarRequisicao(String json, int code){
        return
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(json)
                        .when()
                        .post(SIMULACOES)
                        .then()
                        .log().all()
                        .statusCode(code)
                ;
    }
}
