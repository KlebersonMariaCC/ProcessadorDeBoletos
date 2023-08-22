import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ControllerFatura {
    private List<Fatura> faturas;


   
    public ControllerFatura() {
        this.faturas = new ArrayList<>();
    }

    public Integer criaFatura(String data, double valor, String nome)  {
        LocalDate dataFormatada = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Fatura fatura = new Fatura(dataFormatada,valor,nome);
        if (!faturas.contains(fatura)){
            faturas.add(fatura);    
            return this.faturas.indexOf(fatura);
        }

        return -1;
        
    }

    public Fatura getFatura(Integer indiceFatura) throws Exception {
        try {
             return faturas.get(indiceFatura);
        } catch (Exception e) {
            throw new Exception("Indice inválido");
        }
           
        
    }


    
}
