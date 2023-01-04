package allainTest.utils;

import com.tngtech.java.junit.dataprovider.DataProvider;

import java.util.Random;

public class Constants {

    private final static String BASE_URL = "http://localhost:8080";
    protected final static String RESTRICOES = BASE_URL + "/api/v1/restricoes/";
    protected final static String SIMULACOES = BASE_URL + "/api/v1/simulacoes/";
    protected final static String CPF = "10689077710";
    protected final static String CPF2 = "78925463182";
    protected final static String NOME = "Allain";
    protected final static String EMAIL = "allain@teste.com";
    protected final static Float VALOR = 2000.00F;
    protected final static Integer PARCELAS = 10;
    protected final static Boolean SEGURO = false;

    @DataProvider
    public static Object[][] cpfComRestricao(){
        // os 10 cpfs que estao no documento com restrições
        return new Object[][] {
                {"97093236014"},
                {"60094146012"},
                {"84809766080"},
                {"62648716050"},
                {"26276298085"},
                {"01317496094"},
                {"55856777050"},
                {"19626829001"},
                {"24094592008"},
                {"58063164083"},
        };
    }

    @DataProvider
    public static Object[][] cpfSemRestricao(){
        // 10 cpf aleatorio
        return new Object[][] {
                {"14129934074"},
                {"91681912007"},
                {"84369623030"},
                {"30866741054"},
                {"03693582017"},
                {"22565212062"},
                {"61785794035"},
                {"11098594061"},
                {"00666773009"},
                {"39118212042"},
        };
    }

    public static String sortearCPF() {
        return "0"
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                ;
    }

    public static String sortearCPFComMascara() {
        return "0"
                + "." + new Random().nextInt(9)
                + "." + new Random().nextInt(9)
                + "." + new Random().nextInt(9)
                + "." + new Random().nextInt(9)
                + "." + new Random().nextInt(9)
                + "." + new Random().nextInt(9)
                + "." + new Random().nextInt(9)
                + "." + new Random().nextInt(9)
                + "." + new Random().nextInt(9)
                + "." + new Random().nextInt(9)
                ;
    }

    public static String sortearCPFComLetra() {
        return "test"
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                ;
    }

    public static String sortearCPFComMaisDigitos() {
        return "4321"
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                ;
    }

    public static String sortearCPFComMenosDigitos() {
        return "1"
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                ;
    }
}
