package pratica3_p2;
import java.text.ParseException;

public class Voo {

	private String origem;
	private String destino;
	private String partidaPrevista;
	private String chegadaPrevista;
	private String tempoDeVooString;
	private int tempoDeVooMinutos;
	
	public Voo(String origem, String destino, String partidaPrevista, String chegadaPrevista) throws ParseException {
		this.origem = origem;
		this.destino = destino;
		this.partidaPrevista = partidaPrevista;
		this.chegadaPrevista = chegadaPrevista;
		this.tempoDeVooString = calculaTempoVoo(partidaPrevista, chegadaPrevista);
		this.tempoDeVooMinutos = transformaEmMinutos(this.tempoDeVooString);
	}

	private int transformaEmMinutos(String tempoDeVooString) {
		String[] tempo = tempoDeVooString.split(":");
		return (Integer.parseInt(tempo[0])*60)+(Integer.parseInt(tempo[1]));
	}

	public String calculaTempoVoo(String partidaPrevista, String chegadaPrevista) throws ParseException {
		String[] partida = partidaPrevista.split(" ");
		String[] chegada = chegadaPrevista.split(" ");
		String retorno = calculaHora(partida[1], chegada[1]); 
		return retorno;
	}

	private String calculaHora(String partida, String chegada) {
		String[] string = partida.split(":");
		int horaPartida = Integer.parseInt(string[0]);
		int minutoPartida = Integer.parseInt(string[1]);
		String[] string2 = chegada.split(":");
		int horaChegada = Integer.parseInt(string2[0]);
		int minutoChegada = Integer.parseInt(string2[1]);
		String retorno ="";
		if(horaChegada>=horaPartida)
			retorno = (horaChegada-horaPartida) +":" +(((minutoChegada+60)-minutoPartida)%60);
		else{
			if(minutoPartida == 0) 
				retorno  = ((24-horaPartida)+horaChegada) + ":" + (minutoChegada);
			else
				retorno = ((24-horaPartida)+horaChegada)-1 +":" +(((minutoChegada+60)-minutoPartida)%60);
		}
		return retorno;
	}
	
	public String toString() {
		return origem + " " + this.destino + " " + partidaPrevista + " " + chegadaPrevista + 
				" Tempo de Voo: " + tempoDeVooString + " Tempo de Voo Minutos: " + tempoDeVooMinutos;
	}
	
	public String getOrigem() {
		return this.origem;
	}

	public String getDestino() {
		return this.destino;
	}

	public String getPartidaPrevista() {
		return partidaPrevista;
	}

	public String getChegadaPrevista() {
		return chegadaPrevista;
	}

	public String getTempoDeVooString() {
		return tempoDeVooString;
	}
	
	public int getTempoDeVooMinutos() {
		return tempoDeVooMinutos;
	}
	public String getArq() {
			return this.origem + ";" + this.destino;
	}
}