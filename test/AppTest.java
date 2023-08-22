import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AppTest {
    private ControllerFatura controllerFatura = new ControllerFatura();
    private ControllerBoleto controllerBoleto = new ControllerBoleto();
    private App app = new App(controllerFatura);
    
   
    
    

    @BeforeEach
    void newControllerFatura() throws Exception {
         
        controllerFatura.criaFatura("29/08/2023",1500.0,"João da Silva");
        controllerFatura.criaFatura("10/02/2022", 500, "Kleberson");

        controllerBoleto.criaBoleto("237941200815000" , 600.0, "20/10/2018");
        controllerBoleto.criaBoleto("002", 400.0,  "15/08/2023");
        controllerBoleto.criaBoleto("150089", 600.0,  "15/08/2023");
        app.processarBoletos(0,controllerBoleto);

        

        
        
    }
    
   
    
    @Test
    void testaCadastraFatura () {
        Integer indice = controllerFatura.criaFatura("22/02/2022", 5000, "josé");
        assertEquals(2,indice);
    }

    @Test
    void testaCadastraBoleto() {
         Integer indice = controllerBoleto.criaBoleto("2379412008" , 500.0, "20/10/2018");
        assertEquals(3,indice);
    }

    @Test
    void testaProcessarPagamento() throws Exception {
        assertEquals(app.getPagamento(0), "PAGA");
        assertEquals(app.getTipoPagamento(),"BOLETO");
        assertEquals(app.getPagamento(1), "NÃO PAGA");
    }





}
