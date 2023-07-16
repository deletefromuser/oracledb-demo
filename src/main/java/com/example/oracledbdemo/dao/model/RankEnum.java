package com.example.oracledbdemo.dao.model;

public enum RankEnum {
    BRONZE(0.0),
    SILVER(0.1),
    GOLD(0.2);

    private final double discountRate;

    private RankEnum(double discountRate) {
        this.discountRate = discountRate;
    }

    public int getDiscountPrice(int price) {
        return (int) (price * (1 - discountRate));
    }
}
