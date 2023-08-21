import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @BeforeEach
    void criaFaturas() {
        ControllerFatura controllerFatura = new ControllerFatura();
        controllerFatura.criaFatura("29/06/2023",1500.0,"João da Silva"); 
    }
    
    @Test
    void testMain() {
        assertEquals(1,1);
    }
    
    @Test
    void testaFaturaValida () {

    }




}
