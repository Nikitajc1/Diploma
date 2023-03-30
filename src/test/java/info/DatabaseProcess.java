package info;


import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;

@UtilityClass
public class DatabaseProcess {

    private static Object StatusBuySQL;
    private static Object StatusCreditSQL;
    private static String statusBuy = "select status from payment_entity";
    private static String statusCredit = "select status from credit_request_entity";
    private static QueryRunner runner = new QueryRunner();
    private static Object AmountBuySQL;
    private static String amountField = "select amount from payment_entity";

    private static Connection getConn() throws SQLException {
        String url = System.getProperty("db.url");
        String user = System.getProperty("db.user");
        String pass = System.getProperty("db.pass");
        return DriverManager.getConnection(url, user, pass);
    }

    @SneakyThrows
    public static String buyingStatus() {
        var conn = getConn();
        StatusBuySQL = runner.query(conn, statusBuy, new ScalarHandler<>());
        return (String) StatusBuySQL;
    }

    @SneakyThrows
    public static int buyingAmount() {
        var conn = getConn();
        AmountBuySQL = runner.query(conn, amountField, new ScalarHandler<>());
        return (int) AmountBuySQL;
    }

    @SneakyThrows
    public static String creditStatus() {
        var conn = getConn();
        StatusCreditSQL = runner.query(conn, statusCredit, new ScalarHandler<>());
        return (String) StatusCreditSQL;
    }

    public static void clean() throws SQLException {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}
