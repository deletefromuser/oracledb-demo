package com.example.oracledbdemo.dao.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.oracledbdemo.dao.model.Countries;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class CountriesMapperTest {

    @Autowired
    CountriesMapper mapper;

    @Autowired
    CountriesMapperEx mapperEx;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

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
        Countries country = mapperEx.selectByLike("rgentin");
        log.info("" + country);
    }

    @Test
    void testSplit() {
        List<Countries> country = mapperEx.selectSplit("rgentin,1,2,3,5,6", "1,2,3,5,6,");
        log.info("" + country);
        country = mapperEx.selectSplit("rgentin,1,2,3,5,6", "");
        log.info("" + country);
    }

    @Test
    void testOrder() {
        Countries country = mapperEx.selectOrder("region_Id desc").get(0);
        log.info(country.toString());
        country = mapperEx.selectOrder("region_Id").get(0);
        log.info(country.toString());
    }

    @Test
    void testSharp() {
        Countries country = new Countries();
        country.setRegionId((short) 5);
        Countries result = mapperEx.selectSharp(country).get(0);
        log.info(result.toString());
    }

    @Test
    @Transactional(readOnly = true)
    void testBatch() {
        int batchSize = 10;
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            CountriesMapper cmapper = sqlSession.getMapper(CountriesMapper.class);
            IntStream.range(1, 55).forEach(item -> {
                cmapper.insert(new Countries("" + item, "230211-" + item + "-name",
                        (short) (RandomUtils.nextInt() % 4 + 1)));
                if (item % batchSize == 0) {
                    sqlSession.flushStatements();
                }
            });
            sqlSession.flushStatements();
            sqlSession.commit();
        }

        assertTrue(true);
    }

}
