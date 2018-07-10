package pratica3_p2;

import java.util.ArrayList;
import java.util.Scanner;

public class ServiceVoos {

	private static ArrayList<Voo> melhoresVoos = new ArrayList<>();
	
	public void adicionaVoo(Voo voo) {
		melhoresVoos.add(voo);
	}
	
	public ArrayList<Voo> getMelhoresVoos(){
		return melhoresVoos;
	}

	public boolean verificaVoo(Voo vooDaVez) {
		boolean retorno = true;
		for (Voo voo : melhoresVoos) {
			if (voo.getOrigem().equals(vooDaVez.getOrigem()) && voo.getDestino().equals(vooDaVez.getDestino())
					&& !voo.getTempoDeVooString().equals(vooDaVez.getTempoDeVooString())) {
				voo = comparaVoos(voo, vooDaVez);
				retorno = false;
			} else if (voo.getOrigem().equals(vooDaVez.getOrigem()) && voo.getDestino().equals(vooDaVez.getDestino())
					&& voo.getTempoDeVooString().equals(vooDaVez.getTempoDeVooString()))
				retorno = false;
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

	public void melhorVoo() {
		Scanner sc = new Scanner(System.in);
		String continuar = "sim";
		while (continuar.equalsIgnoreCase("sim")) {
			System.out.printf("Origem: ");
			String origem = sc.nextLine();
			System.out.printf("Destino: ");
			String destino = sc.nextLine();
			if (verifica(origem, destino)) {
				for (Voo voo : melhoresVoos) {
					if (voo.getOrigem().equalsIgnoreCase(origem) && voo.getDestino().equalsIgnoreCase(destino))
						System.out.println("Aeroporto de Origem (Sigla): " + origem + ", Aeroporto de Destino (Sigla): "
								+ destino + ", Tempo Medio de Duracao do Voo: " + voo.getTempoDeVooString());
					else {
						System.out.println(procuraConexao(origem, destino));
					}
				}
				System.out.printf("Deseja continuar a pesquisa? (Sim) ou (Nao): ");
				continuar = sc.nextLine();
			} else {
				System.out.println("Aeroporto de Origem e/ou Destino Nao Cadastrado, digite novamente!");
			}
		}
		sc.close();
	}

	private static boolean verifica(String origem, String destino) {
		boolean existeOrigem = false;
		boolean existeDestino = false;
		for (Voo voo : melhoresVoos) {
			if(voo.getOrigem().equalsIgnoreCase(origem))
				existeOrigem = true;
			if(voo.getDestino().equalsIgnoreCase(destino))
				existeDestino = true;
		}
		return existeOrigem&&existeDestino;
	}

	private static String procuraConexao(String origem, String destino) {
		ArrayList<Voo> parte1 = new ArrayList<>();
		ArrayList<Voo> parte2 = new ArrayList<>();
		String melhorVoo = "";
		int melhorTempo = 100000;
		for (Voo voo : melhoresVoos) {
			if (voo.getDestino().equalsIgnoreCase(destino))
				parte2.add(voo);
		}
		for (Voo voo : melhoresVoos) {
			for (Voo voo2 : parte2) {
				if (origem.equalsIgnoreCase(voo.getOrigem()) && voo.getDestino().equals(voo2.getOrigem()))
					parte1.add(voo);
			}
		}
		for (Voo voo : parte1) {
			for (Voo voo2 : parte2) {
				if (voo.getDestino().equalsIgnoreCase(voo2.getOrigem())
						&& (voo.getTempoDeVooMinutos() + voo2.getTempoDeVooMinutos() <= melhorTempo))
					melhorVoo = "Aeroporto de Origem (Sigla): " + origem + " Aeroporto-Conexao (Sigla): "
							+ voo2.getOrigem() + ", Aeroporto de Destino (Sigla): " + destino
							+ ", Tempo Medio de Duracao do Voo: " + voo.getTempoDeVooString();
			}
		}

		return melhorVoo;
	}
}
