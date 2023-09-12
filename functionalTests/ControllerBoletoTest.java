import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerBoletoTest {

    @Test
    public void testCriaBoletoValorPositivo() throws Exception {
        ControllerBoleto controllerBoleto = new ControllerBoleto();
        Integer indice = controllerBoleto.criaBoleto("B001", 500.0, "01/09/2023");
        assertEquals(0, indice.intValue());
        assertEquals(1, controllerBoleto.getBoletos().size());
    }

    @Test
    public void testCriaBoletoValorZero() throws Exception {
        ControllerBoleto controllerBoleto = new ControllerBoleto();
        Integer indice = controllerBoleto.criaBoleto("B002", 0.0, "02/09/2023");
        assertEquals(0, indice.intValue());
        assertEquals(1, controllerBoleto.getBoletos().size());
    }

    @Test
    public void testCriaBoletoValorNegativo() throws Exception {
        ControllerBoleto controllerBoleto = new ControllerBoleto();
        Integer indice = controllerBoleto.criaBoleto("B003", -200.0, "03/09/2023");
        assertEquals(-1, indice.intValue());
        assertEquals(0, controllerBoleto.getBoletos().size());
    }

    @Test
    public void testCriaBoletoDataInvalida() {
       Exception thrown = assertThrows(Exception.class, () -> {

        ControllerBoleto controllerBoleto = new ControllerBoleto();
        Integer indice = controllerBoleto.criaBoleto("B004", 300.0, "2023/09/01"); // Data inválida
        assertEquals(-1, indice.intValue());
        assertEquals(0, controllerBoleto.getBoletos().size());

       });

       assertEquals("Formato inválido", thrown.getMessage());
    }
}
