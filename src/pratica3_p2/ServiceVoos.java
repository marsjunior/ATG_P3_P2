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
			if(voo.getDestino().equalsIgnoreCase(destino)&&voo.getOrigem().equalsIgnoreCase(origem))
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
			int cont = 0;
			if (verifica(origem) && verifica(destino)) {
				for (Voo voo : melhoresVoos) {
					if (voo.getOrigem().equalsIgnoreCase(origem) && voo.getDestino().equalsIgnoreCase(destino)) {
						toStringVooDireto(voo);
						cont = 0;
						break;
					}
					cont ++;
				}
				if (cont > 0) {
					System.out.println("Procurando Conexao");
					toStringVooConexao(procuraConexao(origem, destino),origem);
				}

				System.out.printf("Deseja continuar a pesquisa? (Sim) ou (Nao): ");
				continuar = sc.nextLine();
			} else {
				throw new Exception("Aeroporto de Origem e/ou Destino Nao Cadastrado, digite novamente!");
			}
		}
		sc.close();		
	}

	private static boolean verifica(String aeroporto) {
		boolean retorno = false;
		if (serviceAeroportos.containsAeroportosBr(aeroporto) || serviceAeroportos.containsAeroportosBrInter(aeroporto)
				|| serviceAeroportos.containsAeroportosEua(aeroporto))
			retorno = true;

		return retorno;
	}

	private static Voo procuraConexao(String origem, String destino) {
		ArrayList<Voo> parte1 = new ArrayList<>();
		Voo melhorVoo = null;
		int melhorTempo = 100000;
		for (Voo voo : melhoresVoos) {
			if (voo.getDestino().equalsIgnoreCase(destino))
				parte1.add(voo);
		}
		
		for (Voo voo : parte1) {
			if (voo.getTempoDeVooMinutos() < melhorTempo) {
				melhorTempo = voo.getTempoDeVooMinutos();
				melhorVoo = voo;
			}
		}

		return melhorVoo;
	}

	private void toStringVooDireto(Voo voo) {
		System.out.println("Aeroporto de Origem (Sigla): " + voo.getOrigem() + ", Aeroporto de Destino (Sigla): "
				+ voo.getDestino() + ", Tempo Medio de Duracao do Voo: " + voo.getTempoDeVooMinutos());

	}

	private void toStringVooConexao(Voo vooConexao, String origem) {
		System.out.println("Aeroporto de Origem (Sigla): " + origem + ", Aeroporto de Destino (Sigla): " + vooConexao.getDestino()
		+ ", Aeroporto Conexao: "+ vooConexao.getOrigem()+", Tempo de Conexao: "+vooConexao.getTempoDeVooMinutos()
		+ ", Tempo Medio de Duracao do Voo: " + (vooConexao.getTempoDeVooMinutos()+getTempoVoo(vooConexao.getOrigem(), origem)));

	}
}
