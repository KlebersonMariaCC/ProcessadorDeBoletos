import java.util.ArrayList;
import java.util.List;

public class App {

    private List<Pagamento> pagamentos;
    private ControllerFatura controllerFatura;
    String tipoPagamento;


    public App(ControllerFatura controllerFatura) {
        this.pagamentos = new ArrayList<>();
        this.controllerFatura = controllerFatura;
    }


    public void processarBoletos(Integer indiceFatura, ControllerBoleto controllerBoleto) {
        Fatura fatura = controllerFatura.get(indiceFatura);
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


    public String getPagamento(int indice) {
        return controllerFatura.get(indice).getStatus();
    }


    public String getTipoPagamento() {
        return tipoPagamento;
    }


    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    

   
}
