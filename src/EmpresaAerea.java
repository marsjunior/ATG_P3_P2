
public class EmpresaAerea {
	
	private String Sigla;
	private String Nome;
	private String Nacionalidade;
	public EmpresaAerea(String sigla, String nome,String nacionalidade) {
		Sigla = sigla;
		Nome = nome;
		this.Nacionalidade = nacionalidade;
	}
	
	public String toString() {
		return Sigla + " " + Nome + " " + Nacionalidade;
	}

	public String getSigla() {
		return Sigla;
	}

	public String getNome() {
		return Nome;
	}

	public String getNacionalidade() {
		return Nacionalidade;
	}
	
}
