

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {
    private ControllerFatura controllerFatura = new ControllerFatura();
    private ControllerBoleto controllerBoleto = new ControllerBoleto();
    private ControllerBoleto controllerBoleto1 = new ControllerBoleto();
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
    void testaCadastraFatura () throws Exception {
        Integer indice = controllerFatura.criaFatura("22/02/2022", 5000, "josé");
        assertEquals(2,indice);
    }

    @Test
    void testaCadastraBoleto() throws Exception {
         Integer indice = controllerBoleto.criaBoleto("2379412008" , 500.0, "20/10/2018");
        assertEquals(3,indice);
    }

    @Test
    void testaProcessarPagamento() throws Exception {
        assertEquals(app.getPagamento(0), "PAGA");
        assertEquals(app.getTipoPagamento(),"BOLETO");
        assertEquals(app.getPagamento(1), "NÃO PAGA");
    }



    // Testes de avl:

    @Test
    public void testFaturaValorMinimo() throws Exception {
        int indiceFatura = controllerFatura.criaFatura("30/09/2023", 0.0, "Cliente Exemplo");

        // Caso a fatura tenha valor mínimo (0), deve ser considerada paga automaticamente.
        app.processarBoletos(indiceFatura, controllerBoleto);

        assertEquals("PAGA", controllerFatura.getFatura(indiceFatura).getStatus());
        assertEquals("BOLETO", app.getTipoPagamento());
    }

        

    @Test
    public void testBoletoValorMinimo() throws Exception {
        int indiceFatura = controllerFatura.criaFatura("30/09/2023", 1500.0, "Cliente Exemplo");
        controllerBoleto.criaBoleto("001", 0.0, "10/09/2023");
    
        app.processarBoletos(indiceFatura, controllerBoleto);
    
        assertEquals("PAGA", controllerFatura.getFatura(indiceFatura).getStatus());
        assertEquals("BOLETO", app.getTipoPagamento());
    }

    @Test
    public void testSomaBoletosValorLimiteInferior() throws Exception {
        int indiceFatura = controllerFatura.criaFatura("30/09/2023", 1500.0, "Cliente Exemplo");
        controllerBoleto.criaBoleto("001", 500.0, "10/09/2023");
        controllerBoleto.criaBoleto("002", 400.0, "15/09/2023");
        controllerBoleto.criaBoleto("003", 600.0, "20/09/2023");
    
        app.processarBoletos(indiceFatura, controllerBoleto);
    
        assertEquals("PAGA", controllerFatura.getFatura(indiceFatura).getStatus());
        assertEquals("BOLETO", app.getTipoPagamento());
    }
    
    
    //testes de PE:

    /** 1 - o testaFaturaValorMInimo e testaBoletoValorMinimo
     *  já testa a partição com valores 0,0
     * 
     * 2- os testes anteriores testam casos com valores  ambos positivos
     */
    


     // Testes TD:

     @Test
     public void testTabelaDecisaoCaso1() throws Exception {
         int indiceFatura = controllerFatura.criaFatura("30/09/2023", 0.0, "Cliente Exemplo");
     
         app.processarBoletos(indiceFatura, controllerBoleto);
     
         assertEquals("PAGA", controllerFatura.getFatura(indiceFatura).getStatus());
         assertEquals("BOLETO", app.getTipoPagamento());
     }
     
     @Test
     public void testTabelaDecisaoCaso2() throws Exception {
        int indiceFatura = controllerFatura.criaFatura("30/09/2023", 1500.0, "Cliente Exemplo");
        
        app.processarBoletos(indiceFatura, controllerBoleto1);
        assertEquals("NÃO PAGA", controllerFatura.getFatura(indiceFatura).getStatus());
        assertEquals("", app.getTipoPagamento());
     }
     
     @Test
     public void testTabelaDecisaoCaso3() throws Exception {
         int indiceFatura = controllerFatura.criaFatura("30/09/2023", 1500.0, "Cliente Exemplo");
         controllerBoleto1.criaBoleto("001", 500.0, "10/09/2023");
     
         app.processarBoletos(indiceFatura, controllerBoleto1);
     
         assertEquals("NÃO PAGA", controllerFatura.getFatura(indiceFatura).getStatus());
         assertEquals("", app.getTipoPagamento());
     }
     
     @Test
     public void testTabelaDecisaoCaso4() throws Exception {
         int indiceFatura = controllerFatura.criaFatura("30/09/2023", 1500.0, "Cliente Exemplo");
         controllerBoleto.criaBoleto("001", 500.0, "10/09/2023");
         controllerBoleto.criaBoleto("002", 400.0, "15/09/2023");
     
         app.processarBoletos(indiceFatura, controllerBoleto1);
     
         assertEquals("NÃO PAGA", controllerFatura.getFatura(indiceFatura).getStatus());
         assertEquals("", app.getTipoPagamento());
     }
     
     @Test
     public void testTabelaDecisaoCaso5() throws Exception {
         int indiceFatura = controllerFatura.criaFatura("30/09/2023", 1500.0, "Cliente Exemplo");
         controllerBoleto.criaBoleto("001", 0.0, "10/09/2023");
     
         app.processarBoletos(indiceFatura, controllerBoleto);
     
         assertEquals("PAGA", controllerFatura.getFatura(indiceFatura).getStatus());
         assertEquals("BOLETO", app.getTipoPagamento());
     }
     
     @Test
     public void testTabelaDecisaoCaso6() throws Exception {
         int indiceFatura = controllerFatura.criaFatura("30/09/2023", 1500.0, "Cliente Exemplo");
         controllerBoleto1.criaBoleto("001", 500.0, "10/09/2023");
         controllerBoleto1.criaBoleto("002", 400.0, "15/09/2023");
         controllerBoleto1.criaBoleto("003", 0.0, "20/09/2023");
     
         app.processarBoletos(indiceFatura, controllerBoleto1);
     
         assertEquals("NÃO PAGA", controllerFatura.getFatura(indiceFatura).getStatus());
         assertEquals("", app.getTipoPagamento());
     }
     
     



}
