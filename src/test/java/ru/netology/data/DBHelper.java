package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static QueryRunner runner = new QueryRunner();

    private DBHelper() {
    }

    @SneakyThrows
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(System.getProperty("source"), System.getProperty("username"), System.getProperty("password"));
    }
    @SneakyThrows
    public static DataHelper.CardStatus getCardStatusPay() {
        String codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        Connection conn = getConnection();
        return new DataHelper.CardStatus(runner.query(conn, codeSQL, new ScalarHandler<String>()));
    }
    @SneakyThrows
    public static DataHelper.CardStatus getCardStatusCredit() {
        String codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        Connection conn = getConnection();
        return new DataHelper.CardStatus(runner.query(conn, codeSQL, new ScalarHandler<String>()));
    }

    @SneakyThrows
    public static void cleanDB() {
        Connection conn = getConnection();
        runner.execute(conn, "DELETE FROM credit_request_entity");
        runner.execute(conn, "DELETE FROM payment_entity");
        runner.execute(conn, "DELETE FROM order_entity");
    }
}
