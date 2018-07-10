package pratica3_p2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static ArrayList<Voo> voos = new ArrayList<>();
	private static ArrayList<EmpresaAerea> empresas = new ArrayList<>();
	private static String[] aeroportosBr = { "SBJP", "SBGR", "SBBR", "SBGL", "SBSV", "SBCF", "SBFZ", "SBEG", "SBPA",
			"SBRF", "SBCT", "SBBE", "SBKP", "SBSG", "SBFL", "SBBV", "SBMQ", "SBPV", "SBSN", "SBCY", "SBCG", "SBMO",
			"SBFI", "SBCB", "SBCZ", "SDSC", "SBRB", "SBTT", "SBPB" };
	private static String[] aeroportosEua = { "KJFK", "KLGA", "KEWR", "KBOS", "KORD", "KIAD", "KDCA", "KATL", "KMCO",
			"KMIA", "KIAH", "KDFW", "KLAX", "KLAS", "PHNL", "KSFO", "KSEA", "KDEN", "KDTW", "KLAN", "KPHX", "KMDW",
			"KSLC" };
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
			if (containsArray(aeroportosBr, voo[4]) && voo[3].equals("I") && containsArray(aeroportosEua, voo[5])) {
				Voo vooDaVez = null;
				if (!voo[6].equals("") && !voo[8].equals(""))
					vooDaVez = new Voo(voo[4], voo[5], voo[6], voo[8]);
				else if (!voo[6].equals("") && !voo[9].equals(""))
					vooDaVez = new Voo(voo[4], voo[5], voo[6], voo[9]);
				else if (!voo[7].equals("") && !voo[8].equals(""))
					vooDaVez = new Voo(voo[4], voo[5], voo[7], voo[8]);
				else if (!voo[7].equals("") && !voo[9].equals(""))
					vooDaVez = new Voo(voo[4], voo[5], voo[7], voo[9]);
				else
					throw new Exception("Erro na Leitura: Voo Sem Hora de Partida/Chegada");
				voos.add(vooDaVez);
			}
		}
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
		for (Voo voo : voos) {
			System.out.println(voo.toString());
		}
		System.out.println("Voos: " + voos.size() + " Aeroportos Br: " + aeroportosBr.length + " Aeroportos Eua: "
				+ aeroportosEua.length);
	}
}
