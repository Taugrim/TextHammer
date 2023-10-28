package bd;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.Optional;

public class SQLiteHelper {
    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:D:/myfin.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static SQLiteHelper instance = null;

    public static synchronized SQLiteHelper getInstance() throws SQLException {
        if (instance == null)
            instance = new SQLiteHelper();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private SQLiteHelper() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public void createLanguageTable(String name) {
        execute("CREATE TABLE " + name + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,word TEXT,comment TEXT);");
    }

    public Optional<ResultSet> execute(String exec) {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(exec)) {
                return Optional.ofNullable(resultSet);
            } catch (SQLException e) {
            }
        } catch (SQLException e) {
        }
        return Optional.empty();
    }
}
