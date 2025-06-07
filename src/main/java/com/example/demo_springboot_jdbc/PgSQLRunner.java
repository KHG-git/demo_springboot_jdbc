package com.example.demo_springboot_jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.ThreadLocalRandom;

/*
docker exec -it my_postgres psql -U kang -d springboot
 */
@Component
public class PgSQLRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try(Connection connection = dataSource.getConnection()) {

            System.out.println("<<<<<<<<<PgSQLRunner>>>>>>>>>");

            System.out.println(dataSource.getClass());
            System.out.println(connection.getMetaData().getDriverName());
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

            System.out.println("<<<<<<<<<PgSQLRunner>>>>>>>>>");

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS TEST_USER(ID INT PRIMARY KEY, NAME VARCHAR(255))";
            statement.executeUpdate(sql);
        }


        int r = ThreadLocalRandom.current().nextInt(1, 10001);

        jdbcTemplate.execute("INSERT INTO TEST_USER VALUES("+r+",'kkk')");

    }
}
