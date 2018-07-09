package pratica3_p2;

public class EmpresaAerea {
	
	private String Sigla;
	private String Nome;
	private String Nacionalidade;
	public EmpresaAerea(String sigla, String nome,String nacionalidade) {
		this.Sigla = sigla;
		this.Nome = nome;
		this.Nacionalidade = nacionalidade;
	}
	
	public String toString() {
		return this.Sigla + " " + this.Nome + " " + this.Nacionalidade;
	}

	public String getSigla() {
		return this.Sigla;
	}

	public String getNome() {
		return this.Nome;
	}

	public String getNacionalidade() {
		return this.Nacionalidade;
	}
	
}
