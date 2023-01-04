package allainTest.suite;

import allainTest.tests.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RestricoesTest.class,
        ConsultarSimulacaoCPFTest.class,
        CriarSimulacaoTest.class,
        AlterarSimulacaoTest.class,
        ConsultarSimulacaoCPFTest.class,
        RemoverSimulacaoTest.class
})
public class TestSuite {


}
