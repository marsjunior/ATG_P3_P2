package pratica3_p2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static ArrayList<Voo> melhoresVoos = new ArrayList<>();
	private static ArrayList<EmpresaAerea> empresas = new ArrayList<>();
	private static String[] aeroportosBrInter = { "SBJP", "SBGR", "SBBR", "SBGL", "SBSV", "SBCF", "SBFZ", "SBEG", "SBPA",
			"SBRF", "SBCT", "SBBE", "SBKP", "SBSG", "SBFL", "SBBV", "SBMQ", "SBPV", "SBSN", "SBCY", "SBCG", "SBMO",
			"SBFI", "SBCB", "SBCZ", "SDSC", "SBRB", "SBTT", "SBPB" };
	
	private static String[] aeroportosBr = {"SBSP", "SBRJ", "SBVT", "SBGO", "SBPS", "SBAR", "SBTE", "SBUL", "SBRP",
			"SBLO", "SBBH", "SBRB", "SBJU", "SBMG", "SBMO", "SSPS", "SBJV", "SBCX", "SBPL", "SBKG", "SBSN", "SBIL", "SBIZ",
			"SBIZ", "SBCH", "SBMA", "SBDN", "SBDN", "SJTC", "SBMK"};

	private static String[] aeroportosEua = { "KJFK", "KLGA", "KEWR", "KBOS", "KORD", "KIAD", "KDCA", "KATL", "KMCO",
			"KMIA", "KIAH", "KDFW", "KLAX", "KLAS", "PHNL", "KSFO", "KSEA", "KDEN", "KDTW", "KLAN", "KPHX", "KMDW",
			"KSLC", "SBJP", "SBGR", "SBBR", "SBGL", "SBSV", "SBCF", "SBFZ", "SBEG", "SBPA",
			"SBRF", "SBCT", "SBBE", "SBKP", "SBSG", "SBFL", "SBBV", "SBMQ", "SBPV", "SBSN", "SBCY", "SBCG", "SBMO",
			"SBFI", "SBCB", "SBCZ", "SDSC", "SBRB", "SBTT", "SBPB"};
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
			if (containsArray(aeroportosBrInter, voo[4]) && (containsArray(aeroportosBr, voo[5]) || containsArray(aeroportosEua, voo[5]))) {
				Voo vooDaVez = null;
				if (!voo[6].equals("") && !voo[8].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[6], voo[8]);
					if(verificaVoo(vooDaVez))
						melhoresVoos.add(vooDaVez);
				}else if (!voo[6].equals("") && !voo[9].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[6], voo[9]);
					if(verificaVoo(vooDaVez))
						melhoresVoos.add(vooDaVez);
				}else if (!voo[7].equals("") && !voo[8].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[7], voo[8]);
					if(verificaVoo(vooDaVez))
						melhoresVoos.add(vooDaVez);
				}else if (!voo[7].equals("") && !voo[9].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[7], voo[9]);
					if(verificaVoo(vooDaVez))
						melhoresVoos.add(vooDaVez);
				}
				
			}
		}
	}

	private static boolean verificaVoo(Voo vooDaVez) {
		boolean retorno = true;
		for (Voo voo : melhoresVoos) {
			if(voo.getOrigem().equals(vooDaVez.getOrigem())
					&&voo.getDestino().equals(vooDaVez.getDestino())
					&&!voo.getTempoDeVoo().equals(vooDaVez.getTempoDeVoo())) {
				voo = melhorVoo(voo, vooDaVez);
				retorno = false;
			}else if(voo.getOrigem().equals(vooDaVez.getOrigem())
					&&voo.getDestino().equals(vooDaVez.getDestino())
					&&voo.getTempoDeVoo().equals(vooDaVez.getTempoDeVoo()))
				retorno = false;
		}	
		return retorno;
	}

	private static Voo melhorVoo(Voo voo1, Voo voo2) {
		Voo retorno = voo1;
		String[] stringVoo1 = voo1.getTempoDeVoo().split(":");
		String[] stringVoo2 = voo2.getTempoDeVoo().split(":");
		int tempoVoo1 = Integer.parseInt(stringVoo1[0])*60 + Integer.parseInt(stringVoo1[1]);
		int tempoVoo2 = Integer.parseInt(stringVoo2[0])*60 + Integer.parseInt(stringVoo2[1]);
		if(tempoVoo1>tempoVoo2)
			retorno = voo2;
		return retorno;
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
		for (Voo voo : melhoresVoos) {
			System.out.println(voo.toString());
		}
		System.out.println("Voos: " + melhoresVoos.size() + " Aeroportos Br: " + aeroportosBr.length + " Aeroportos Eua: "
				+ aeroportosEua.length);
	}
}
