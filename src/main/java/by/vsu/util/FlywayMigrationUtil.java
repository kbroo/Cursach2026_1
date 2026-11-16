package by.vsu.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

@WebListener
public class FlywayMigrationUtil implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            DataSource dataSource = DBUtil.getDataSource();
            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            flyway.migrate();
            System.out.println("Была проведена миграция базы данных.");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка миграции базы данных", e);
        }
    }
}
