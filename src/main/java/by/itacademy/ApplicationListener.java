package by.itacademy;


import lombok.extern.log4j.Log4j2;
import org.h2.tools.Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.sql.SQLException;

@Log4j2

@WebListener
public class ApplicationListener implements ServletContextListener {

    // for connect with UI tool to database use url - jdbc:h2:tcp://localhost/mem:jdbc
    private static Server SERVER;

    public void contextInitialized(ServletContextEvent event) {

        System.setProperty("log4j.configurationFile", "logger/log4j2.xml");

        try {
            SERVER = Server.createTcpServer().start();
            log.debug("H2 Server started");

        } catch (SQLException e) {
            e.printStackTrace();
            log.debug("Error starting H2 Server", e);
        }

        //SERVER.stop();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
