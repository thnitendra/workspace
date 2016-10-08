package nit.poc.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.spi.NamingManager;

public class Main {
	public static void main(String[] args) {
		try {
			NamingManager.setInitialContextFactoryBuilder(new LocalContext());			
			Context ctx = new InitialContext();
			//ctx.bind("key1", "val1");
			Object obj = ctx.lookup("key1");
			System.out.println(obj);
			ctx.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
