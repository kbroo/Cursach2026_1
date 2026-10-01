package by.vsu.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream is = DBUtil.class.getClassLoader()
                .getResourceAsStream("db.properties")) {

            Properties props = new Properties();
            props.load(is);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");

            Class.forName(props.getProperty("db.driver"));

        } catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить настройки БД", e);
        }
    }

    private DBUtil() {}

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
