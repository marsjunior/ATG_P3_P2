package pratica3_p2;

public class ServiceAeroportos {
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

	
	public String[] getAeroportosBrInter() {
		return aeroportosBrInter;
	}
	public String[] getAeroportosBr() {
		return aeroportosBr;
	}
	public String[] getAeroportosEua() {
		return aeroportosEua;
	}
	
	public boolean containsAeroportosBrInter(String aeroporto) {
		boolean retorno = false;
		for (int i = 0; i < aeroportosBrInter.length; i++) {
			if(aeroportosBrInter[i].equalsIgnoreCase(aeroporto))
				retorno = true;
		}
		return retorno;
	}
	public boolean containsAeroportosBr(String aeroporto) {
		boolean retorno = false;
		for (int i = 0; i < aeroportosBr.length; i++) {
			if(aeroportosBr[i].equalsIgnoreCase(aeroporto))
				retorno = true;
		}
		return retorno;
	}
	public boolean containsAeroportosEua(String aeroporto) {
		boolean retorno = false;
		for (int i = 0; i < aeroportosEua.length; i++) {
			if(aeroportosEua[i].equalsIgnoreCase(aeroporto))
				retorno = true;
		}
		return retorno;
	}
}
