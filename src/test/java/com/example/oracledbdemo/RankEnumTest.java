package com.example.oracledbdemo;

import org.junit.jupiter.api.Test;

import com.example.oracledbdemo.dao.model.RankEnum;

import lombok.extern.slf4j.Slf4j;

/** @see https://qiita.com/suke_masa/items/bd242617a2b9bb773cbd */
@Slf4j
public class RankEnumTest {
    @Test
    void testGetDiscountPrice() {
        RankEnum test = RankEnum.BRONZE;
        switch (test) {
            case BRONZE:
                log.info("yes");
                break;
            default:
                log.info("no");

        }
    }

    @Test
    void testValueOf() {
        log.info(RankEnum.valueOf(RankEnum.class, "BRONZE").toString());
    }

    @Test
    void testValues() {
        for (RankEnum rank : RankEnum.values()) {
            log.info(rank.toString());
        }
    }
}
