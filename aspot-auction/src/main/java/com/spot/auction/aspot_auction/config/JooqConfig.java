package com.spot.auction.aspot_auction.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class JooqConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public DSLContext dslContext() {
        return DSL.using(dataSource, SQLDialect.MYSQL);
    }
}
