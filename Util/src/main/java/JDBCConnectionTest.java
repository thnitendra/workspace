import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;

/**
 * Created by nitendra.thakur on 2/26/18.
 */
public class JDBCConnectionTest {
    public static void main(String[] args) throws Exception {

        getConnectionUsingDS();

    }

    private static void getConnectionBasic() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.ReplicationDriver");
        String url = "jdbc:oracle:thin:@//myhost:1521/mydb";

        Connection conn =
                DriverManager.getConnection(url,"myschema","mypassw");

        conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();
        ResultSet rset =
                stmt.executeQuery("select BANNER from SYS.V_$VERSION");
        while (rset.next()) {
            System.out.println (rset.getString(1));
        }
        stmt.close();

        System.out.println ("Success!");
    }
    private static void getConnectionUsingDS() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:8889/pointparts?useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);

        Connection conn = dataSource.getConnection();
        conn.setReadOnly(true);
    }
}
