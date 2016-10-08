package nit.soft.partner.dao;

import java.sql.SQLException;

import javax.sql.XADataSource;

import com.arjuna.ats.internal.jdbc.DynamicClass;

public class XADataSourceLookup implements DynamicClass {

	@Override
	public XADataSource getDataSource(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		com.arjuna.ats.jdbc.TransactionalDriver td = new com.arjuna.ats.jdbc.TransactionalDriver();
		
		return null;
	}

}
