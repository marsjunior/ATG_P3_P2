package pratica3_p2;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

	private static Scanner scanner;
	private static ServiceVoos serviceVoos = new ServiceVoos();
	private static ServiceAeroportos serviceAeroportos = new ServiceAeroportos();

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
			if (containsArray(serviceAeroportos.getAeroportosBrInter(), voo[4])
					&& (containsArray(serviceAeroportos.getAeroportosBr(), voo[5])
							|| containsArray(serviceAeroportos.getAeroportosEua(), voo[5]))) {
				Voo vooDaVez = null;
				if (!voo[6].equals("") && !voo[8].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[6], voo[8]);
					if (serviceVoos.verificaVooMelhor(vooDaVez) == null)
						serviceVoos.adicionaVoo(vooDaVez);
				} else if (!voo[6].equals("") && !voo[9].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[6], voo[9]);
					if (serviceVoos.verificaVooMelhor(vooDaVez) == null)
						serviceVoos.adicionaVoo(vooDaVez);
				} else if (!voo[7].equals("") && !voo[8].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[7], voo[8]);
					if (serviceVoos.verificaVooMelhor(vooDaVez) == null)
						serviceVoos.adicionaVoo(vooDaVez);
				} else if (!voo[7].equals("") && !voo[9].equals("")) {
					vooDaVez = new Voo(voo[4], voo[5], voo[7], voo[9]);
					if (serviceVoos.verificaVooMelhor(vooDaVez) == null)
						serviceVoos.adicionaVoo(vooDaVez);
				}

			}
		}
	}

	public static void main(String[] args) throws Exception {
		readVoos();

		scanner.close();
		for (Voo voo : serviceVoos.getMelhoresVoos()) {
			System.out.println(voo.toString());
		}
		serviceVoos.melhorVoo();
		System.out.println("Voos: " + serviceVoos.getMelhoresVoos().size() + " Aeroportos Br: "
				+ serviceAeroportos.getAeroportosBr().length + " Aeroportos Eua: "
				+ serviceAeroportos.getAeroportosEua().length);
		String path = System.getProperty("user.dir");
		File file = new File(path + "/grafo.csv");
		file.createNewFile();
		FileWriter arquivo = new FileWriter(file);

		for (Voo voo : serviceVoos.getMelhoresVoos()) {
			arquivo.write(voo.getArq() + "\n");
		}
		arquivo.flush();
		arquivo.close();
		int total = serviceAeroportos.getAeroportosBr().length + serviceAeroportos.getAeroportosBrInter().length
				+ serviceAeroportos.getAeroportosEua().length;
		System.out.println("Voos: " + serviceVoos.getMelhoresVoos().size() + " Aeroportos Br: "
				+ serviceAeroportos.getAeroportosBr().length + " Aeroportos Br Inter: "
				+ serviceAeroportos.getAeroportosBrInter().length + " Aeroportos Eua: "
				+ serviceAeroportos.getAeroportosEua().length + " Vertices Total: " + total);
	}
}
