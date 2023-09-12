import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControllerBoleto {
    private List<Boleto> boletos;



    public ControllerBoleto() {
        this.boletos = new ArrayList<>();
    }



    public Integer criaBoleto(String codigo, double valor, String data) throws Exception {
       try {
            LocalDate dataFormatada = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (valor >=0) {
                Boleto boleto = new Boleto(codigo,valor,dataFormatada);
                if (!boletos.contains(boleto)){
                    boletos.add(boleto);    
                    return this.boletos.indexOf(boleto);
                }
            }
       } catch (Exception e) {
        throw new Exception("Formato inválido");
       }

        return -1;
    }



    public List<Boleto> getBoletos() {
        return this.boletos;
    }

}
