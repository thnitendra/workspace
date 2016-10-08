package nit.poc.jndi;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;

class LocalContext extends InitialContext implements InitialContextFactoryBuilder, InitialContextFactory {

	private Map<Object, Object> datastore;
	
	public LocalContext() throws NamingException {
		super();
		datastore = new HashMap<Object,Object>();
	}
	
	public void addToDatastore(Object key, Object value) {
		datastore.put(key, value);
	}
	
	/*@Override
	public void bind(String name, Object obj) throws NamingException {
		addToDatastore(name, obj);
	}*/

	public InitialContextFactory createInitialContextFactory(
			Hashtable<?, ?> hsh) throws NamingException {
		datastore.putAll(hsh);
		return this;
	}

	public Context getInitialContext(Hashtable<?, ?> arg0)
			throws NamingException {
		return this;
	}

	@Override
	public Object lookup(String name) throws NamingException {
		Object ret = datastore.get(name);
		return (ret != null) ? ret : super.lookup(name);
	}	
}