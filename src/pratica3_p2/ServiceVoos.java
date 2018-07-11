package pratica3_p2;

import java.util.ArrayList;
import java.util.Scanner;

public class ServiceVoos {

	private static ArrayList<Voo> melhoresVoos = new ArrayList<>();
	private static ServiceAeroportos serviceAeroportos = new ServiceAeroportos();
	private Scanner sc;

	public void adicionaVoo(Voo voo) {
		melhoresVoos.add(voo);
	}

	public ArrayList<Voo> getMelhoresVoos() {
		return melhoresVoos;
	}

	public Voo verificaVooMelhor(Voo vooDaVez) {
		Voo retorno = null;
		for (Voo voo : melhoresVoos) {
			if (voo.getOrigem().equals(vooDaVez.getOrigem()) && voo.getDestino().equals(vooDaVez.getDestino()))
				retorno = comparaVoos(voo, vooDaVez);
		}

		return retorno;
	}

	private int getTempoVoo(String destino, String origem) {
		int retorno = 0;
		for (Voo voo : melhoresVoos) {
			if (voo.getDestino().equals(destino) && voo.getOrigem().equals(origem))
				retorno = voo.getTempoDeVooMinutos();
		}
		return retorno;
	}

	private static Voo comparaVoos(Voo voo1, Voo voo2) {
		Voo retorno = null;
		if (voo1.getTempoDeVooMinutos() > voo2.getTempoDeVooMinutos())
			retorno = voo2;
		else
			retorno = voo1;
		return retorno;
	}

	public void melhorVoo() throws Exception {
		sc = new Scanner(System.in);
		String continuar = "sim";
		while (continuar.equalsIgnoreCase("sim")) {
			System.out.printf("Origem: ");
			String origem = sc.nextLine();
			System.out.printf("Destino: ");
			String destino = sc.nextLine();
			boolean vooDireto = false;
			if (verifica(origem) && verifica(destino)) {
				for (Voo voo : melhoresVoos) {
					if (voo.getOrigem().equalsIgnoreCase(origem) && voo.getDestino().equalsIgnoreCase(destino)) {
						toStringVooDireto(voo);
						vooDireto = true;
						break;
					}
				}
				if (!vooDireto) {
					System.out.println("Procurando Conexao");
					Voo conexao2 = procuraConexao(origem, destino, 2);
					Voo conexao1 = procuraConexao(origem, conexao2.getOrigem(), 1);
					toStringVooConexao(conexao2, conexao1);
				}

				System.out.printf("Deseja continuar a pesquisa? (Sim) ou (Nao): ");
				continuar = sc.nextLine();
			} else {
				throw new Exception("Aeroporto de Origem e/ou Destino Nao Cadastrado, digite novamente!");
			}
		}
		sc.close();
	}

	private static Voo procuraConexao(String origem, String destino, int conexao) {
		ArrayList<Voo> parte1 = new ArrayList<>();
		ArrayList<Voo> parte2 = new ArrayList<>();
		int melhorTempo = 100000;
		Voo conexao1 = null;
		Voo conexao2 = null;
		Voo vooRetorno = null;

		for (Voo voo : melhoresVoos) {
			if (origem.equals(voo.getOrigem()))
				parte1.add(voo);
			if (destino.equals(voo.getDestino()))
				parte2.add(voo);
		}

		for (Voo voo : parte1) {
			for (Voo voo2 : parte2) {
				int tempo = voo.getTempoDeVooMinutos() + voo2.getTempoDeVooMinutos();
				if (voo.getDestino().equals(voo2.getOrigem()) && tempo < melhorTempo) {
					conexao1 = voo;
					conexao2 = voo2;
					melhorTempo = voo.getTempoDeVooMinutos()+voo2.getTempoDeVooMinutos();
				}
			}
		}

		if (conexao == 1)
			vooRetorno = conexao1;
		else
			vooRetorno = conexao2;
		return vooRetorno;
	}

	private static boolean verifica(String aeroporto) {
		boolean retorno = false;
		if (serviceAeroportos.containsAeroportosBr(aeroporto) || serviceAeroportos.containsAeroportosBrInter(aeroporto)
				|| serviceAeroportos.containsAeroportosEua(aeroporto))
			retorno = true;

		return retorno;
	}

	private void toStringVooDireto(Voo voo) {
		System.out.println("Aeroporto de Origem (Sigla): " + voo.getOrigem() + ", Aeroporto de Destino (Sigla): "
				+ voo.getDestino() + ", Tempo Medio de Duracao do Voo: " +getTempoVoo(voo.getDestino(), voo.getOrigem()));

	}

	private void toStringVooConexao(Voo vooConexao2, Voo vooConexao1) {
		System.out.println("Aeroporto de Origem (Sigla): " + vooConexao1.getOrigem()
				+ ", Aeroporto de Destino (Sigla): " + vooConexao2.getDestino() + ", Aeroporto Conexao: "
				+ vooConexao2.getOrigem() + ", Tempo de Conexao 1: "
				+ getTempoVoo(vooConexao1.getDestino(), vooConexao1.getOrigem()) + ", Tempo de Conexao 2: "
				+ vooConexao2.getTempoDeVooMinutos() + ", Tempo Medio de Duracao do Voo: "
				+ (vooConexao2.getTempoDeVooMinutos() + vooConexao1.getTempoDeVooMinutos()));

	}
}
