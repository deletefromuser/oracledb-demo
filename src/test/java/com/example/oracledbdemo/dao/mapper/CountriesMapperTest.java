package com.example.oracledbdemo.dao.mapper;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.oracledbdemo.dao.model.Countries;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class CountriesMapperTest {

    @Autowired
    CountriesMapper mapper;

    @Autowired
    CountriesMapperEx mapperEx;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() {
        Countries country = mapper.selectByPrimaryKey("CA");
        log.info(country.toString());
    }

    @Test
    void testLike() {
        Countries country = mapperEx.selectByLike("na");
        log.info(country.toString());
    }

    @Test
    void testOrder() {
        Countries country = mapperEx.selectOrder("regionId desc").get(0);
        log.info(country.toString());
        country = mapperEx.selectOrder("regionId").get(0);
        log.info(country.toString());
    }

}
