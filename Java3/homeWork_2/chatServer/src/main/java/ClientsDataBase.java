import java.sql.*;

public class ClientsDataBase {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/geek_chat";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "geek";

    public static void run() {
        if (registerDriver()) return;

        Connection connection = addConnection();

        closeConnection(connection);
    }

    private static boolean registerDriver() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Невозможно получить класс. Драйвер не найден");
            e.printStackTrace();
            return true;
        }
        return false;
    }

    static Connection addConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, LOGIN, PASSWORD);
        } catch (SQLException throwables) {
            System.out.println("Не удалось установить соединение. Неверный URL.");
            throwables.printStackTrace();
        }
        return connection;
    }

    private static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            System.out.println("Невозможно закрыть соединение.");
            throwables.printStackTrace();
            return;
        }
    }
}
