import java.util.ArrayList;
import java.util.List;

public class ControllerBoleto {
    private List<Boleto> boletos;



    public ControllerBoleto() {
        this.boletos = new ArrayList<>();
    }



    public Integer criaBoleto(String codigo, double valor, String data) {
        
        Boleto boleto = new Boleto(codigo,valor,data);
        if (!boletos.contains(boleto)){
            boletos.add(boleto);    
            return this.boletos.indexOf(boleto);
        }

        return -1;
    }



    public List<Boleto> getBoletos() {
        return this.boletos;
    }

}
