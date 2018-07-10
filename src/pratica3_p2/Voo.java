package pratica3_p2;
import java.text.ParseException;

public class Voo {

	private String origem;
	private String destino;
	private String partidaPrevista;
	private String chegadaPrevista;
	private String tempoDeVoo;
	
	public Voo(String origem, String destino, String partidaPrevista, String chegadaPrevista) throws ParseException {
		this.origem = origem;
		this.destino = destino;
		this.partidaPrevista = partidaPrevista;
		this.chegadaPrevista = chegadaPrevista;
		this.tempoDeVoo = calculaTempoVoo(partidaPrevista, chegadaPrevista);
	}

	public String calculaTempoVoo(String partidaPrevista, String chegadaPrevista) throws ParseException {
//		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
//        java.util.Date dataInicio = sdFormat.parse(partidaPrevista);
//        java.util.Date dataFim = sdFormat.parse(chegadaPrevista);
//        double diff = dataFim.getTime() - dataInicio.getTime();
//        double resultado = diff / (1000*60*60);
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
		return origem + " " + destino + " " + partidaPrevista + " " + chegadaPrevista + " Tempo de Voo: " + tempoDeVoo;
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

	public String getTempoDeVoo() {
		return tempoDeVoo;
	}
	public String getArq() {
		return this.origem + ";" + this.destino;
	}
}
