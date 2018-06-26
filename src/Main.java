import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static ArrayList<Voo> voos = new ArrayList<>();
	private static ArrayList<EmpresaAerea> empresas = new ArrayList<>();
	
	public static void readVoos() throws FileNotFoundException {
		String path = System.getProperty("user.dir");
		FileReader  file = new FileReader(path + "/vra-02_2018.csv");
		Scanner scanner = new Scanner(file);
		
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] voo = linha.split(";");
            Voo vooDaVez = new Voo(voo[4], voo[5], voo[6], voo[8]);
            voos.add(vooDaVez);
        }
	}
	
	public static void readEmpresas() throws FileNotFoundException {
		String path = System.getProperty("user.dir");
		FileReader  file = new FileReader(path + "/glossario_de_empresas_aereas.csv");
		Scanner scanner = new Scanner(file);
		
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] empresa = linha.split(";");
            EmpresaAerea empresaDaVez  = new EmpresaAerea(empresa[0], empresa[1]);
            empresas.add(empresaDaVez);
        }
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		readVoos();
		readEmpresas();
		for(int i = 0; i < empresas.size(); i++) {
			System.out.println(empresas.get(i).toString());
		}
	}
}
