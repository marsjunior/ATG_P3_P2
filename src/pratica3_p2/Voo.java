package pratica3_p2;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Voo {

	private String origem;
	private String destino;
	private String partidaPrevista;
	private String chegadaPrevista;
	private long tempoDeVoo;
	private Calendar c1 = Calendar.getInstance();
	private Calendar c2 = Calendar.getInstance();
	
	public Voo(String origem, String destino, String partidaPrevista, String chegadaPrevista) throws ParseException {
		this.origem = origem;
		this.destino = destino;
		this.partidaPrevista = partidaPrevista;
		this.chegadaPrevista = chegadaPrevista;
		this.tempoDeVoo = calculaTempoVoo(partidaPrevista, chegadaPrevista);
	}

	public long calculaTempoVoo(String partidaPrevista, String chegadaPrevista) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		c1.setTime(sdf.parse(partidaPrevista));
		c2.setTime(sdf.parse(chegadaPrevista));
		long horas = c2.getTimeInMillis() - c1.getTimeInMillis() / 3600000;
		long minutos = (c2.getTimeInMillis() - c1.getTimeInMillis() - horas * 3600000) / 60000;
		long diferenca = horas + minutos;
		return diferenca;
	}

	public String toString() {
		return origem + " " + destino + " " + partidaPrevista + " " + chegadaPrevista + " Tempo de Voo:" + tempoDeVoo;
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

	public long getTempoDeVoo() {
		return tempoDeVoo;
	}
}