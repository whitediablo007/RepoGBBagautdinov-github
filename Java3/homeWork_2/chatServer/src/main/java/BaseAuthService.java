import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class BaseAuthService implements AuthService {
    Map<String, User> users;
    ClientsDataBase baseApp;

    @Override
    public void start() {
        users = new HashMap<>();
        baseApp = new ClientsDataBase();
        baseApp.run();
        try {
            Statement statement = baseApp.addConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from auth");
            while (resultSet.next()) {
                users.put(resultSet.getString("login"),
                        new User(resultSet.getString("login"),
                                resultSet.getString("pass"),
                                resultSet.getString("nick")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getNickByLoginPass(String login, String password) {
        User user = users.get(login);
        if (user != null && user.getPassword().equals(password)) {
            return user.getNick();
        }
        return null;
    }

    @Override
    public void stop() {
        System.out.println("Сервис остановлен");
    }
}
