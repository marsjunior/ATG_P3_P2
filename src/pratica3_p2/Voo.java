package pratica3_p2;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Voo {

	private String origem;
	private String destino;
	private String partidaPrevista;
	private String chegadaPrevista;
	private double tempoDeVoo;
	
	public Voo(String origem, String destino, String partidaPrevista, String chegadaPrevista) throws ParseException {
		this.origem = origem;
		this.destino = destino;
		this.partidaPrevista = partidaPrevista;
		this.chegadaPrevista = chegadaPrevista;
		this.tempoDeVoo = calculaTempoVoo(partidaPrevista, chegadaPrevista);
	}

	public double calculaTempoVoo(String partidaPrevista, String chegadaPrevista) throws ParseException {
		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        java.util.Date dataInicio = sdFormat.parse(partidaPrevista);
        java.util.Date dataFim = sdFormat.parse(chegadaPrevista);
        double diff = dataFim.getTime() - dataInicio.getTime();
        double resultado = diff / (1000*60*60);
		return resultado;
	}

	public String toString() {
		return origem + " " + destino + " " + partidaPrevista + " " + chegadaPrevista + " Tempo de Voo: " + format(tempoDeVoo);
	}
	
	private String format(double x) {  
	    return String.format("%.1f", x);  
	}
	
	public String getOrigem() {
		return origem;
	}

	public String getDestino() {
		return destino;
	}

	public String getPartidaPrevista() {
		return partidaPrevista;
	}

	public String getChegadaPrevista() {
		return chegadaPrevista;
	}

	public double getTempoDeVoo() {
		return tempoDeVoo;
	}
}