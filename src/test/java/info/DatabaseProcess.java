package info;

import com.microsoft.sqlserver.jdbc.ISQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.mysql.jdbc.Driver;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.postgresql.util.DriverInfo;

import javax.sql.DataSource;
import java.sql.*;

@UtilityClass
public class DatabaseProcess{

    private static Object StatusBuySQL;
    private static Object StatusCreditSQL;
    private static String statusBuy = "select status from payment_entity group by id order by max(created) desc limit 1";
    private static String statusCredit = "select status from credit_request_entity group by id order by max(created) desc limit 1";
    private static String user = "app";
    private static String pass = "pass";
    private static QueryRunner runner = new QueryRunner();

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(getUrl(), user, pass);
    }

    public static String buyingStatus() {

        try {
            var conn = getConn();
            StatusBuySQL = runner.query(conn, statusBuy, new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (String) StatusBuySQL;
    }

    public static String creditStatus() {

        try {
            var conn = getConn();
            StatusCreditSQL = runner.query(conn, statusCredit, new ScalarHandler<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (String) StatusCreditSQL;
    }

    public static void clean() throws SQLException {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }


    private static String getUrl() {
//        SQLServerDataSource dataSource = new SQLServerDataSource();
//        dataSource.setUser("app");
//        dataSource.setPassword("pass");
//        dataSource.setServerName("localhost");
//        dataSource.setDatabaseName("app");
//        try {
//            var c = dataSource.getConnection();
//        } catch (SQLServerException e) {
//            throw new RuntimeException(e);
//        }
        String i = "jdbc:mysql://localhost:3306/app";
        return i;
//        return System.getProperty("test.db.url");
    }
}
