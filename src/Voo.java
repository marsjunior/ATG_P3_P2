
public class Voo {
	
	private String origem;
	private String destino;
	private String PartidaPrevista;
	private String ChegadaPrevista;
	
	public Voo(String origem, String destino, String partidaPrevista, String chegadaPrevista) {
		this.origem = origem;
		this.destino = destino;
		PartidaPrevista = partidaPrevista;
		ChegadaPrevista = chegadaPrevista;
	}
	
	public String toString() {
		return origem + " " + destino + " " + PartidaPrevista + " " + ChegadaPrevista;
	}
	
}