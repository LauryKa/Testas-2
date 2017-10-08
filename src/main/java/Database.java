
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database {

    private BasicDataSource dataSource;

    private static final String USERS_TABLE = "Testas";

    public Database() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/kcs");
        dataSource.setMaxIdle(1);
        dataSource.setInitialSize(1);
        dataSource.setValidationQuery("SELECT 1");
    }

    public void createTable(String tableName) {
        String sql = "CREATE TABLE " + tableName + " (id INTEGER NOT NULL AUTO_INCREMENT, "
                + "vardas VARCHAR(255), pavarde TEXT, adresas TEXT, email TEXT, PRIMARY KEY(id))";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Ivyko klaida: " + e.getLocalizedMessage());
        }
    }


    public void insert(String vardas, String pavarde, String adresas, String email) {
        String sql = "INSERT INTO " + USERS_TABLE + " (vardas, pavarde, adresas, email)  VALUES "
                + "(?, ?, ?, ?)";
        System.out.println("veikia");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vardas);
            statement.setString(2, pavarde);
            statement.setString(3, adresas);
            statement.setString(4, email);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Ivyko klaida: " + e.getLocalizedMessage());
        }
    }


    public String select(String info) {
        String sql = "SELECT * FROM " + USERS_TABLE + " WHERE vardas= '" + info + "' OR pavarde='" + info +
                "' OR email='" + info + "'";
        String fullInfo = "Tokio vartotojo nera";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String vardas = resultSet.getString("vardas");
                String pavarde = resultSet.getString("pavarde");
                String adresas = resultSet.getString("adresas");
                String email = resultSet.getString("email");
                fullInfo = vardas + " " + pavarde + " " + adresas + " " + email;

            }
        } catch (SQLException e) {
            System.out.println("Ivyko klaida: " + e.getLocalizedMessage());
        }
        return fullInfo;
    }







}