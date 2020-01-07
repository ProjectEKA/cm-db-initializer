package org.projecteka.cm;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        System.out.println("Creating schema for consent manager");
        java.sql.Connection connection = null;
        Database database = null;
        try {
            connection = openConnection(); //your openConnection logic here
            database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new liquibase.Liquibase("liquibase.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection openConnection() throws Exception {
        String jdbcUrl = System.getProperty("jdbc.url");
        String jdbcUsername = System.getProperty("jdbc.username");
        String jdbcUserPwd = System.getProperty("jdbc.password");
        if (isEmptyString(jdbcUrl) || isEmptyString(jdbcUsername) || isEmptyString(jdbcUserPwd)) {
            throw new Exception("you must set jdbc.url, jdbc.username, jdbc.password as properties, either using -D option or setting as env");
        }
        return DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcUserPwd);
    }

    private static boolean isEmptyString(String value) {
        return (value == null) || "".equals(value.trim());
    }
}
