package pratica3_p2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static ArrayList<Voo> melhoresVoos = new ArrayList<>();
	private static ArrayList<EmpresaAerea> empresas = new ArrayList<>();
	private static String[] aeroportosBrInter = { "SBJP", "SBGR", "SBBR", "SBGL", "SBSV", "SBCF", "SBFZ", "SBEG",
			"SBPA", "SBRF", "SBCT", "SBBE", "SBKP", "SBSG", "SBFL", "SBBV", "SBMQ", "SBPV", "SBSN", "SBCY", "SBCG",
			"SBMO", "SBFI", "SBCB", "SBCZ", "SDSC", "SBRB", "SBTT", "SBPB" };

	private static String[] aeroportosBr = { "SBSP", "SBRJ", "SBVT", "SBGO", "SBPS", "SBAR", "SBTE", "SBUL", "SBRP",
			"SBLO", "SBBH", "SBRB", "SBJU", "SBMG", "SBMO", "SSPS", "SBJV", "SBCX", "SBPL", "SBKG", "SBSN", "SBIL",
			"SBIZ", "SBIZ", "SBCH", "SBMA", "SBDN", "SBDN", "SJTC", "SBMK" };

	private static String[] aeroportosEua = { "KJFK", "KLGA", "KEWR", "KBOS", "KORD", "KIAD", "KDCA", "KATL", "KMCO",
			"KMIA", "KIAH", "KDFW", "KLAX", "KLAS", "PHNL", "KSFO", "KSEA", "KDEN", "KDTW", "KLAN", "KPHX", "KMDW",
			"KSLC", "SBJP", "SBGR", "SBBR", "SBGL", "SBSV", "SBCF", "SBFZ", "SBEG", "SBPA", "SBRF", "SBCT", "SBBE",
			"SBKP", "SBSG", "SBFL", "SBBV", "SBMQ", "SBPV", "SBSN", "SBCY", "SBCG", "SBMO", "SBFI", "SBCB", "SBCZ",
			"SDSC", "SBRB", "SBTT", "SBPB" };
	private static Scanner scanner;

	private static boolean containsArray(String[] array, String aeroporto) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(aeroporto)) {
				return true;
			}
		}
		return false;
	}

	public static void readVoos() throws Exception {
		String path = System.getProperty("user.dir");
		FileReader file = new FileReader(path + "/vra-02_2018.csv");
		scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			String linha = scanner.nextLine();
			String[] voo = linha.split(";");
			if (containsArray(aeroportosBrInter, voo[4])
					&& (containsArray(aeroportosBr, voo[5]) || containsArray(aeroportosEua, voo[5]))) {
				Voo vooDaVez = null;
				if (!voo[6].equals("") && !voo[8].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[6], voo[8]);
					if (verificaVoo(vooDaVez))
						melhoresVoos.add(vooDaVez);
				} else if (!voo[6].equals("") && !voo[9].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[6], voo[9]);
					if (verificaVoo(vooDaVez))
						melhoresVoos.add(vooDaVez);
				} else if (!voo[7].equals("") && !voo[8].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[7], voo[8]);
					if (verificaVoo(vooDaVez))
						melhoresVoos.add(vooDaVez);
				} else if (!voo[7].equals("") && !voo[9].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[7], voo[9]);
					if (verificaVoo(vooDaVez))
						melhoresVoos.add(vooDaVez);
				}

			}
		}
	}

	private static boolean verificaVoo(Voo vooDaVez) {
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

	private static void melhorVoo() {
		Scanner sc = new Scanner(System.in);
		String continuar = "sim";
		while (continuar.equalsIgnoreCase("sim")) {
			System.out.printf("Origem: ");
			String origem = sc.nextLine();
			System.out.printf("Destino: ");
			String destino = sc.nextLine();			
			for (Voo voo : melhoresVoos) {
				if (voo.getOrigem().equalsIgnoreCase(origem) && voo.getDestino().equalsIgnoreCase(destino))
					System.out.println("Aeroporto de Origem (Sigla): " + origem + ", Aeroporto de Destino (Sigla): " + destino
							+ ", Tempo Medio de Duracao do Voo: " + voo.getTempoDeVooString());
			}
			System.out.printf("Deseja continuar a pesquisa? (Sim) ou (Nao): ");
			continuar = sc.nextLine();
		}
		sc.close();
	}

	public static void readEmpresas() throws FileNotFoundException {
		String path = System.getProperty("user.dir");
		FileReader file = new FileReader(path + "/glossario_de_empresas_aereas.csv");
		scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			String linha = scanner.nextLine();
			String[] empresa = linha.split(",");
			if (empresa[2].equals("BRASILEIRA")) {
				EmpresaAerea empresaDaVez = new EmpresaAerea(empresa[0], empresa[1], empresa[2]);
				empresas.add(empresaDaVez);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		readVoos();
		readEmpresas();
		scanner.close();
		for (Voo voo : melhoresVoos) {
			System.out.println(voo.toString());
		}
		melhorVoo();
		System.out.println("Voos: " + melhoresVoos.size() + " Aeroportos Br: " + aeroportosBr.length
				+ " Aeroportos Eua: " + aeroportosEua.length);		
	}
}
