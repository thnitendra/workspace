package nit.poc.xa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/**
 * 2 phase commit with XA enabled jdbc driver
 * @see https://www.progress.com/jdbc/resources/tutorials/understanding-jta/distributed-transactions-and-the-transaction-manager
 * 
 */
public class XA_2PC {
	
	XADataSource xaDS;
	XAConnection xaCon;
	XAResource xaRes;
	Xid xid;
	Connection con;
	Statement stmt;
	int ret;
	
	private XADataSource getDataSource() {
		MysqlXADataSource xads = new MysqlXADataSource();
		xads.setUrl("jdbc:mysql://localhost:3306/nitdb");
		xads.setUser("mysql");
		xads.setPassword("mysql123");
		return xads;
	}
	
	public void init() throws SQLException {
		xaDS = getDataSource();
		xaCon = xaDS.getXAConnection("mysql", "mysql123");
		xaRes = xaCon.getXAResource();
		con = xaCon.getConnection();
		stmt = con.createStatement();
		xid = new MyXid(100, new byte[]{0x01}, new byte[]{0x02});
	}
	
	public void close() {
		try {
			stmt.close();
		} catch (Exception e) {}
		try {
			con.close();
		} catch (Exception e) {}
		try {
			xaCon.close();
		} catch (Exception e) {}
	}
	
	public void testConnection() throws SQLException {
		ResultSet rs = stmt.executeQuery("select user() from dual");
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
		System.out.println("OK");
	}
	
	/**
	 * Example 1—This example uses the two-phase commit protocol to commit one transaction branch:
	 * @throws SQLException
	 * @throws XAException
	 */
	public void example1() throws SQLException, XAException {		
		xaRes.start(xid, XAResource.TMNOFLAGS);
		stmt.executeUpdate("insert into test_table values (100)");
		xaRes.end(xid, XAResource.TMSUCCESS);
			
		ret = xaRes.prepare(xid);
		if (ret == XAResource.XA_OK) {
			xaRes.commit(xid, false);
		}		
	}
	
	/**
	 * Example 2—example, similar to Example 1, illustrates a rollback:
	 * @throws SQLException
	 * @throws XAException
	 */
	public void example2() throws SQLException, XAException {		
		xaRes.start(xid, XAResource.TMNOFLAGS);
		stmt.executeUpdate("insert into test_table values (100)");
		xaRes.end(xid, XAResource.TMSUCCESS);
		ret = xaRes.prepare(xid);
		if (ret == XAResource.XA_OK) {
		 xaRes.rollback(xid);
		}		
	}
	
	/**
	 * Example 3—This example shows how a distributed transaction branch suspends, lets the same connection do a local transaction, and them resumes the branch later. The two-phase commit actions of distributed transaction do not affect the local transaction.
	 * @throws SQLException
	 * @throws XAException
	 */
	public void example3() throws SQLException, XAException {
		xid = new MyXid(100, new byte[]{0x01}, new byte[]{0x02});

		xaRes.start(xid, XAResource.TMNOFLAGS);
		stmt.executeUpdate("insert into test_table values (100)");
		xaRes.end(xid, XAResource.TMSUSPEND);

		// This update is done outside of transaction scope, so it
		// is not affected by the XA rollback.
		stmt.executeUpdate("insert into test_table2 values (111)");

		xaRes.start(xid, XAResource.TMRESUME);
		stmt.executeUpdate("insert into test_table values (200)");
		xaRes.end(xid, XAResource.TMSUCCESS);

		ret = xaRes.prepare(xid);
		if (ret == XAResource.XA_OK) {
		 xaRes.rollback(xid);
		}
	}
	
	/**
	 * Example 4—This example illustrates how one XA resource can be shared among different transactions. Two transaction branches are created, but they do not belong to the same distributed transaction. JTA allows the XA resource to do a two-phase commit on the first branch even though the resource is still associated with the second branch.
	 * @throws SQLException
	 * @throws XAException
	 */
	public void example4() throws SQLException, XAException {
		Xid xid1 = xid;
		Xid xid2 = new MyXid(100, new byte[]{0x11}, new byte[]{0x22});

		xaRes.start(xid1, XAResource.TMNOFLAGS);
		stmt.executeUpdate("insert into test_table1 values (100)");
		xaRes.end(xid1, XAResource.TMSUCCESS);

		xaRes.start(xid2, XAResource.TMNOFLAGS);

		// Should allow XA resource to do two-phase commit on
		// transaction 1 while associated to transaction 2
		ret = xaRes.prepare(xid1);
		if (ret == XAResource.XA_OK) {
		 xaRes.commit(xid1, false);
		}

		stmt.executeUpdate("insert into test_table2 values (200)");
		xaRes.end(xid2, XAResource.TMSUCCESS);

		ret = xaRes.prepare(xid2);
		if (ret == XAResource.XA_OK) {
		 xaRes.rollback(xid2);
		}
	}
	
	/**
	 * Example 5—This example illustrates how transaction branches on different connections can be joined as a single branch if they are connected to the same resource manager. This feature improves distributed transaction efficiency because it reduces the number of two-phase commit processes. Two XA connections to the same database server are created. Each connection creates its own XA resource, regular JDBC connection, and statement. Before the second XA resource starts a transaction branch, it checks to see if it uses the same resource manager as the first XA resource uses. If this is case, as in this example, it joins the first branch created on the first XA connection instead of creating a new branch. Later, the transaction branch can be prepared and committed using either XA resource.
	 * @throws SQLException
	 * @throws XAException
	 */
	public void example5() throws SQLException, XAException {		
		xaRes.start(xid, XAResource.TMNOFLAGS);
		stmt.executeUpdate("insert into test_table1 values (100)");
		xaRes.end(xid, XAResource.TMSUCCESS);

		XAConnection xaCon2 = xaDS.getXAConnection("mysql", "mysql123");
		XAResource xaRes2 = xaCon2.getXAResource();
		Connection con2 = xaCon2.getConnection();
		Statement stmt2 = con2.createStatement();

		if (xaRes2.isSameRM(xaRes)) {
		 xaRes2.start(xid, XAResource.TMJOIN);
		 stmt2.executeUpdate("insert into test_table2 values (100)");
		 xaRes2.end(xid, XAResource.TMSUCCESS);
		}
		else {
		 Xid xid2 = new MyXid(100, new byte[]{0x01}, new byte[]{0x03});
		 xaRes2.start(xid2, XAResource.TMNOFLAGS);
		 stmt2.executeUpdate("insert into test_table2 values (100)");
		 xaRes2.end(xid2, XAResource.TMSUCCESS);
		 ret = xaRes2.prepare(xid2);
		 if (ret == XAResource.XA_OK) {
			 xaRes2.commit(xid2, false);
		 }
		}
	}
	
	/**
	 * Example 6—This example shows how to recover prepared or heuristically completed transaction branches during failure recovery. It first tries to rollback each branch; if it fails, it tries to tell resource manager to discard knowledge about the transaction.
	 * @throws XAException
	 */
	public void example6() throws XAException {
		Xid[] xids;

		xids = xaRes.recover(XAResource.TMSTARTRSCAN | XAResource.TMENDRSCAN);
		for (int i=0; xids!=null && i<xids.length; i++) {
			try {
				xaRes.rollback(xids[i]);
			}
			catch (XAException ex) {
				try {
					xaRes.forget(xids[i]);
				}
				catch (XAException ex1) {
					System.out.println("rollback/forget failed: " +  ex1.errorCode);
				}
			}
		}
	}
}
