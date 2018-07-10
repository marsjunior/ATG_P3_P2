package pratica3_p2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Main {
	
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
	private static ServiceVoos service = new ServiceVoos();

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
					if (service.verificaVoo(vooDaVez))
						service.adicionaVoo(vooDaVez);
				} else if (!voo[6].equals("") && !voo[9].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[6], voo[9]);
					if (service.verificaVoo(vooDaVez))
						service.adicionaVoo(vooDaVez);
				} else if (!voo[7].equals("") && !voo[8].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[7], voo[8]);
					if (service.verificaVoo(vooDaVez))
						service.adicionaVoo(vooDaVez);
				} else if (!voo[7].equals("") && !voo[9].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[7], voo[9]);
					if (service.verificaVoo(vooDaVez))
						service.adicionaVoo(vooDaVez);
				}

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

		scanner.close();
		for (Voo voo : service.getMelhoresVoos()) {
			System.out.println(voo.toString());
		}
		service.melhorVoo();
		System.out.println("Voos: " + service.getMelhoresVoos().size() + " Aeroportos Br: " + aeroportosBr.length
				+ " Aeroportos Eua: " + aeroportosEua.length);
		String path = System.getProperty("user.dir");
 		File file = new File(path + "/grafo.csv");
 		file.createNewFile();
 		FileWriter arquivo = new FileWriter(file);
 		
 		for (Voo voo : service.getMelhoresVoos()) {
 			arquivo.write(voo.getArq() + "\n");
 		}
 		arquivo.flush();
 		arquivo.close();
 		int total = aeroportosBr.length + aeroportosBrInter.length + aeroportosEua.length;
 		System.out.println("Voos: " + service.getMelhoresVoos().size() + " Aeroportos Br: " + aeroportosBr.length + " Aeroportos Br Inter: " + aeroportosBrInter.length + " Aeroportos Eua: "
+ aeroportosEua.length + " Vertices Total: " + total); 
	}
}
