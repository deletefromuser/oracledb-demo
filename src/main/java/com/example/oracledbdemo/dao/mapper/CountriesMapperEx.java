package com.example.oracledbdemo.dao.mapper;

import java.util.List;

import com.example.oracledbdemo.dao.model.Countries;

public interface CountriesMapperEx {

    Countries selectByLike(String countryName);
    List<Countries> selectOrder(String orderName);
    List<Countries> selectSplit(String countryName, String regionId);
    List<Countries> selectSharp(Countries countries);
}