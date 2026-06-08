package example.testing;

import io.opentelemetry.api.GlobalOpenTelemetry;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import io.opentelemetry.instrumentation.jdbc.datasource.JdbcTelemetry;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:8989/postgres";
        String user = "postgres";
        String password = "postgres";

        var dataSource = new PGSimpleDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        var jdbcWrapper = JdbcTelemetry.create(GlobalOpenTelemetry.get()).wrap(dataSource);

        try (Connection connection = jdbcWrapper.getConnection()) {
            System.out.println("Connected to PostgreSQL test database");

            String sql = "SELECT version()";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    System.out.println("PostgreSQL version: " + resultSet.getString(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to PostgreSQL");
            e.printStackTrace();
        }
    }
}