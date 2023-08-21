import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private ControllerFatura controllerFatura = new ControllerFatura();
    private ControllerBoleto controllerBoleto = new ControllerBoleto();
   
    
    

    @BeforeEach
    void newControllerFatura() {
         
        controllerFatura.criaFatura("29/08/2023",1500.0,"João da Silva");
        controllerFatura.criaFatura("10/02/2022", 500, "Kleberson");
        
        
    }
    
   
    
    @Test
    void testaCadastraFatura () {
        Integer indice = controllerFatura.criaFatura("22/02/2022", 5000, "josé");
        assertEquals(2,indice);
    }

    @Test
    void testaCadastraBoleto() {
         Integer indice = controllerBoleto.criaBoleto(2379412008 , 500, "20/10/2018");
        assertEquals(2,indice);
    }




}
