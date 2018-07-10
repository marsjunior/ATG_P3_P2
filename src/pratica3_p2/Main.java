package pratica3_p2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static ArrayList<Voo> melhoresVoos = new ArrayList<>();
	private static ArrayList<EmpresaAerea> empresas = new ArrayList<>();
	private static String[] aeroportosBrInter = { "SBGL", "SBBR", "SBGR", "SBRF", "SBJP", "SBSV", "SBCF", "SBFZ", "SBEG",
			"SBPA", "SBCT", "SBBE", "SBKP", "SBSG", "SBFL", "SBBV", "SBMQ", "SBPV", "SBSN", "SBCY", "SBCG", "SBMO",
			"SBFI", "SBCB", "SBCZ", "SDSC", "SBRB", "SBTT", "SBPB", "SBGO", "SBAR", "SBTE"};
	
	private static String[] aeroportosBr = {"SBSP", "SBRJ", "SBVT", "SBPS", "SBUL", "SBRP",
			"SBLO", "SBBH", "SBRB", "SBJU", "SBMG", "SBJV", "SBCX", "SBPL", "SBKG", "SBIZ",
			"SBCH", "SBMA", "SBDN", "SBDN", "SBMK"};

	private static String[] aeroportosEua = { "KBWI", "KCLT", "KORD", "KDFW", "KFTW", "KIAH", "KATL", "KJFK", "KBOS", "KEWR", "KMIA", "KORL"};
	private static String[] aeroportosArgentina = { "SABE", "SACO", "SAEZ", "SAME", "SAAR", "SANT"};
	private static String[] aeroportosMexico = { "MMUN", "MMMX", "MMGL", "MMMD"};
	private static String[] aeroportosColombia = { "SKBQ", "SKBO", "SKCL", "SKRG"};
	private static String[] aeroportosBolivia = { "SLCB", "SLLP", "SLVR"};
	private static String[] aeroportosVenezuela = { "SVBC", "SVMI", "SVVA"};
	private static String[] aeroportosChile = { "SCDA", "SCEL"};
	private static String[] aeroportosUruguai = { "SULS", "SUMU"};
	private static String[] aeroportosParaguai = { "SGAS", "SGES"};
	private static String[] aeroportosEquador = { "SEGU", "SEQM"};
	private static String[] aeroportosRepDominicana = { "MDPC", "MDSD"};
	private static String[] aeroportosPanama = { "MPTO"};
	private static String[] aeroportosCostaRica = { "MROC"};
	private static String[] aeroportosCuba = { "MUHA"};
	private static String[] aeroportosGuatemala = { "MGGT"};
	private static String[] aeroportosPortoRico = { "TJSJ"};
	private static String[] aeroportosPeru = { "SPJC"};
	private static String[] aeroportosCanada = { "CYYZ"};

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
			if (containsArray(aeroportosBrInter, voo[4]) && (containsArray(aeroportosBr, voo[5]) || containsArray(aeroportosEua, voo[5]) || containsArray(aeroportosArgentina, voo[5]) || containsArray(aeroportosMexico, voo[5])
					|| containsArray(aeroportosColombia, voo[5]) || containsArray(aeroportosBolivia, voo[5]) || containsArray(aeroportosVenezuela, voo[5]) || containsArray(aeroportosChile, voo[5]) || containsArray(aeroportosUruguai, voo[5])
					|| containsArray(aeroportosParaguai, voo[5]) || containsArray(aeroportosEquador, voo[5]) || containsArray(aeroportosRepDominicana, voo[5]) || containsArray(aeroportosPanama, voo[5]) || containsArray(aeroportosCostaRica, voo[5])
					|| containsArray(aeroportosCuba, voo[5]) || containsArray(aeroportosGuatemala, voo[5]) || containsArray(aeroportosPortoRico, voo[5]) || containsArray(aeroportosPeru, voo[5]) || containsArray(aeroportosCanada, voo[5]))) {
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
