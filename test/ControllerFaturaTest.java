import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerFaturaTest {

    @Test
    public void testCriaFaturaValorPositivo() throws Exception {
        ControllerFatura controllerFatura = new ControllerFatura();
        Integer indice = controllerFatura.criaFatura("01/09/2023", 1500.0, "Cliente A");
        assertEquals(0, indice.intValue());
    }

    @Test
    public void testCriaFaturaValorZero() throws Exception {
        ControllerFatura controllerFatura = new ControllerFatura();
        Integer indice = controllerFatura.criaFatura("02/09/2023", 0.0, "Cliente B");
        assertEquals(0, indice.intValue());
    }

    @Test
    public void testCriaFaturaValorNegativo() throws Exception {
        ControllerFatura controllerFatura = new ControllerFatura();
        Integer indice = controllerFatura.criaFatura("03/09/2023", -200.0, "Cliente C");
        assertEquals(-1, indice.intValue());
    }

    @Test
    public void testCriaFaturaDataInvalida() throws Exception {
        Exception thrown = assertThrows(Exception.class, () -> {
            ControllerFatura controllerFatura = new ControllerFatura();
            Integer indice = controllerFatura.criaFatura("2023/09/01", 1000.0, "Cliente D"); // Data inválida
        });

        assertEquals("Formato inválido", thrown.getMessage());

    }

    @Test
    public void testGetFaturaExistente() throws Exception {
        ControllerFatura controllerFatura = new ControllerFatura();
        controllerFatura.criaFatura("04/09/2023", 800.0, "Cliente E");
        Fatura fatura = controllerFatura.getFatura(0);
        assertNotNull(fatura);
        assertEquals("Cliente E", fatura.getNome());
    }

    @Test
    public void testGetFaturaInexistente() {
        ControllerFatura controllerFatura = new ControllerFatura();
        assertThrows(Exception.class, () -> controllerFatura.getFatura(0)); // Índice inválido
    }
}
