package nit.poc.xa;

public class Main {
	public static void main(String[] args) {
		XA_2PC xa = new XA_2PC();
		try {
			xa.init();
			//xa.testConnection();
			xa.example2();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			xa.close();
		}
		
	}
}
