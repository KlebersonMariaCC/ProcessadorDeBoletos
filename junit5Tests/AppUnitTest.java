import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppUnitTest {
    private ControllerFatura controllerFatura;
    private ControllerBoleto controllerBoleto;
    private App app;

    @BeforeEach
    void setUp() throws Exception {
        controllerFatura = new ControllerFatura();
        controllerBoleto = new ControllerBoleto();
        app = new App(controllerFatura);
        // Configurar o estado inicial, criar faturas e boletos conforme necessário
    }

    @Test
    void testProcessamentoDeBoletosComSucesso2() throws Exception {
        // Crie faturas e boletos
        int indiceFatura = controllerFatura.criaFatura("01/09/2023", 1500.0, "Cliente A");
        controllerBoleto.criaBoleto("B001", 1500.0, "01/09/2023");

        // Execute o processamento de boletos
        app.processarBoletos(indiceFatura, controllerBoleto);

        // Verifique se a fatura foi marcada como "PAGA" e o tipo de pagamento é "BOLETO"
        assertEquals("PAGA", controllerFatura.getFatura(indiceFatura).getStatus());
        assertEquals("BOLETO", app.getTipoPagamento());
    }

    @Test
    void testProcessamentoDeBoletosComFalha2() throws Exception {
        // Crie faturas e boletos
        int indiceFatura = controllerFatura.criaFatura("01/09/2023", 1500.0, "Cliente A");
        controllerBoleto.criaBoleto("B002", 1000.0, "02/09/2023");

        // Execute o processamento de boletos
        app.processarBoletos(indiceFatura, controllerBoleto);

        // Verifique se a fatura não foi marcada como "PAGA" e o tipo de pagamento está vazio
        assertEquals("NÃO PAGA", controllerFatura.getFatura(indiceFatura).getStatus());
        assertEquals("", app.getTipoPagamento());
    }

    @Test
    void testProcessamentoDeBoletosParaFaturaInexistente2() {
        // Tente processar boletos para uma fatura que não existe
        Exception thrown = assertThrows(Exception.class, () -> {
            app.processarBoletos(0, controllerBoleto);
        });

        assertEquals("Índice inválido",thrown.getMessage());
    }

    @Test
    void testObtencaoDeFaturaExistente() throws Exception {
        // Crie uma fatura
        int indiceFatura = controllerFatura.criaFatura("01/09/2023", 1500.0, "Cliente A");

        // Obtenha a fatura pelo índice
        Fatura faturaObtida = controllerFatura.getFatura(indiceFatura);

        // Verifique se a fatura obtida é a mesma que a criada
        assertNotNull(faturaObtida);
        assertEquals("Cliente A", faturaObtida.getNome());
    }

    @Test
    void testObtencaoDeFaturaInexistente2() {
        // Tente obter uma fatura com um índice que não existe
        assertThrows(Exception.class, () -> {
            controllerFatura.getFatura(0);
        });
    }

    @Test
    void testCriaBoletoValorZero() throws Exception {
        
        controllerBoleto.criaBoleto("B003", 0.0, "03/09/2023");
        
        assertEquals(0,controllerBoleto.getBoletos().get(0).getValor());
    }

    @Test
    void testCriaBoletoValorNegativo2() throws Exception {
       
        Integer indice = controllerBoleto.criaBoleto("B004", -200.0, "04/09/2023");
        assertEquals(-1, indice);
    }

    @Test
    void testCriaBoletoDataInvalida2() {
        // Tente criar um boleto com uma data no formato inválido
        Exception thrown = assertThrows(Exception.class, () -> {
            controllerBoleto.criaBoleto("B005", 300.0, "2023/09/01");
        });

        assertEquals("Formato inválido", thrown.getMessage());
    }

    @Test
    void testCriaFaturaComValorPositivo2() throws Exception {
        // Crie uma fatura com valor positivo
        int indiceFatura = controllerFatura.criaFatura("05/09/2023", 1000.0, "Cliente B");

        // Verifique se a fatura é adicionada à lista de faturas corretamente
        Fatura fatura = controllerFatura.getFatura(indiceFatura);
        assertNotNull(fatura);
        assertEquals("Cliente B", fatura.getNome());
    }

    @Test
    void testCriaFaturaComValorNegativo2() throws Exception {
        Integer indice = controllerFatura.criaFatura("06/09/2023", -500.0, "Cliente C");
        assertEquals(-1, indice);
    }

    @Test
    void testCriaFaturaDataInvalida2() {
        // Tente criar uma fatura com uma data no formato inválido
        Exception thrown = assertThrows(Exception.class, () -> {
            controllerFatura.criaFatura("2023/09/07", 800.0, "Cliente D");
        });
        assertEquals("Formato inválido", thrown.getMessage());
    }

    @Test
    void testCriaBoletoComValorPositivo2() throws Exception {
        // Crie um boleto com valor positivo
        controllerBoleto.criaBoleto("B006", 700.0, "07/09/2023");

        // Verifique se o boleto é adicionado à lista de boletos corretamente
        assertEquals(700.0, controllerBoleto.getBoletos().get(0).getValor());
    }

    @Test
    void testCriaBoletoComValorZero2() throws Exception {
        // Tente criar um boleto com valor zero
       
        controllerBoleto.criaBoleto("B007", 0.0, "08/09/2023");

        assertEquals(0,controllerBoleto.getBoletos().get(0).getValor());
    }

    @Test
    void testCriaBoletoComDataInvalida3() {
        // Tente criar um boleto com uma data no formato inválido
        Exception thrown = assertThrows(Exception.class, () -> {
            controllerBoleto.criaBoleto("B008", 600.0, "2023/09/09");
        });
        assertEquals("Formato inválido", thrown.getMessage());
    }
}
