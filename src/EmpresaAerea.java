
public class EmpresaAerea {
	
	private String Sigla;
	private String Nome;
	
	public EmpresaAerea(String sigla, String nome) {
		Sigla = sigla;
		Nome = nome;
	}
	
	public String toString() {
		return Sigla + " " + Nome;
	}

}
