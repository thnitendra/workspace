package nit.soft.ejb.client.partner;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import nit.soft.ejb.PartnerHome;
import nit.soft.ejb.PartnerRemote;

public class EJBApplicationClient {
	private Context context;
	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
	
	public EJBApplicationClient() throws NamingException {
		Properties properties = new Properties();
		properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
		
		// 1. Obtaining Context
        context = new InitialContext(properties);        
    }
  
	public static void main(String[] args) {
		try {
			EJBApplicationClient appClient = new EJBApplicationClient();
			appClient.test();
			appClient.context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void test() {
        PartnerRemote bean = null;
        try {
            // 2. Generate JNDI Lookup name
            //String lookupName = getLookupName();tabase
        	//String lookupName = "ejb:/ejb-partner-0.0.1-SNAPSHOT/PartnerBean!nit.soft.ejb.PartnerHome";
        	//String lookupName = "ejb:/partner-ear-0.0.1-SNAPSHOT/partner-ejb-0.0.1-SNAPSHOT/PartnerBean!nit.soft.ejb.PartnerHome";
        	String lookupName = "ejb:/partner-ejb-0.0.1-SNAPSHOT/PartnerBean!nit.soft.ejb.PartnerHome";
            // 3. Lookup and cast
        	PartnerHome beanHome = (PartnerHome) context.lookup(lookupName);
        	bean = beanHome.create();
        	//bean = (PartnerRemote) context.lookup(lookupName);
            // 4. Call business logic
            System.out.println(bean.check());            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private static String getLookupName() {
		/* 
		The app name is the EAR name of the deployed EJB without .ear suffix. 
		Since we haven't deployed the application as a .ear, 
		the app name for us will be an empty string
		*/
    	String appName = "";
 
        /* The module name is the JAR name of the deployed EJB 
        without the .jar suffix.
        */
        String moduleName = "ejb-partner-0.0.1-SNAPSHOT";
 
		/*AS7 allows each deployment to have an (optional) distinct name. 
		This can be an empty string if distinct name is not specified.
		*/
        String distinctName = "";
 
        // The EJB bean implementation class name
        String beanName = PartnerRemote.class.getSimpleName();
 
        // Fully qualified remote interface name
        final String interfaceName = PartnerRemote.class.getName();
 
        // Create a look up string name
        String name = "ejb:" + appName + "/" + moduleName + "/" + 
            distinctName    + "/" + beanName + "!" + interfaceName;
 
        return name;
    }
}