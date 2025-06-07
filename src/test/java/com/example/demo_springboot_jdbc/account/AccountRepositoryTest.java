package com.example.demo_springboot_jdbc.account;

import com.example.demo_springboot_jdbc.DemoSpringbootJdbcApplication;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//SpringBootTest 테스트를 하면 postgreSQL을 사용하게 됨
//SpringBootTest는 통합테스트라서 모든 Bean을 등록함
//@SpringBootTest(classes= DemoSpringbootJdbcApplication.class)
//Slicing Test = DataJpaTest 는 H2 DB를 사용하게 됨.,
@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() throws SQLException {

        try( Connection connection = dataSource.getConnection()) {

            DatabaseMetaData metaData = connection.getMetaData();

            System.out.println("<<<<<<<<<AccountRepositoryTest>>>>>>>>>");
            System.out.println(metaData.getURL());
            System.out.println(metaData.getDriverName());
            System.out.println(metaData.getUserName());
            System.out.println("<<<<<<<<<AccountRepositoryTest>>>>>>>>>");

        }


        Account account = new Account();
        account.setUsername("kang");
        account.setPassword("pass");

        Account newAccount = accountRepository.save(account);

        assertThat(newAccount).isNotNull();

        //Account existingAccount = accountRepository.findByUsername(newAccount.getUsername());
        Optional<Account> existingAccount = accountRepository.findByUsername(newAccount.getUsername());
        //assertThat(existingAccount).isNotNull();
        assertThat(existingAccount).isNotEmpty();

        //Account nonExistingAccount = accountRepository.findByUsername("kim");
        Optional<Account>  nonExistingAccount = accountRepository.findByUsername("kim");
        //assertThat(nonExistingAccount).isNull();
        assertThat(nonExistingAccount).isEmpty();





    }


}