import java.util.ArrayList;
import java.util.List;

public class ControllerFatura {
    private List<Fatura> faturas;




   
    public ControllerFatura() {
        this.faturas = new ArrayList<>();
    }

    public Integer criaFatura(String data, double valor, String nome)  {
        
        Fatura fatura = new Fatura(data,valor,nome);
        if (!faturas.contains(fatura)){
            faturas.add(fatura);    
            return this.faturas.indexOf(fatura);
        }

        return -1;
        
    }

    public Fatura get(Integer indiceFatura) {
        return faturas.get(indiceFatura);
    }


    
}
