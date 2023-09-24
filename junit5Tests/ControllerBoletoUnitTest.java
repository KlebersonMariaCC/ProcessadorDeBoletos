import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class ControllerBoletoUnitTest {

    private ControllerBoleto controllerBoleto;

   @BeforeEach
    void setUp() throws Exception {
        controllerBoleto = new ControllerBoleto();
    }

    @Test
    void testCriaBoletoComValorPositivo() throws Exception {
        Integer indice = controllerBoleto.criaBoleto("B001", 700.0, "01/09/2022");
        assertEquals(0, indice.intValue());
        assertEquals(1, controllerBoleto.getBoletos().size());
    }

    @Test
    void testCriaBoletoComValorZero() throws Exception {
        controllerBoleto.criaBoleto("B002", 0.0, "05/08/2022");
        assertEquals(0, controllerBoleto.getBoletos().get(0).getValor());
    }

    @Test
    void testCriaBoletoComValorNegativo() throws Exception {
        
        Integer indice = controllerBoleto.criaBoleto("B003", -200.0, "02/05/2021");
        
        assertEquals(-1, indice);
    }

    @Test
    void testCriaBoletoComDataInvalida() {
        Exception thrown = assertThrows(Exception.class, () -> {
            controllerBoleto.criaBoleto("B004", 300.0, "2021/05/02"); // Data inválida
        });
        assertEquals("Formato inválido", thrown.getMessage());
    }
}