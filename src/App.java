

public class App {

    
    private ControllerFatura controllerFatura;
    String tipoPagamento;


    public App(ControllerFatura controllerFatura) {
        this.controllerFatura = controllerFatura;
    }


    public void processarBoletos(Integer indiceFatura, ControllerBoleto controllerBoleto) throws Exception {
        Fatura fatura = controllerFatura.getFatura(indiceFatura);
        double somaBoletos = 0;
        double valorFatura = fatura.getValor();
        this.tipoPagamento = "";

        for(Boleto boleto : controllerBoleto.getBoletos()) {
            somaBoletos += boleto.getValor();
            if (somaBoletos >= valorFatura) {
                fatura.setStatus("PAGA");
                this.setTipoPagamento("BOLETO");
            }
            
        }

    }


    public String getPagamento(int indice) throws Exception {
        try {
            return controllerFatura.getFatura(indice).getStatus();
        } catch (Exception e) {
           throw new Exception("Índice inválido");
        }
        
        
    }


    public String getTipoPagamento() {
        return tipoPagamento;
    }


    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    

   
}
