package com.example.oracledbdemo.dao.mapper;

import com.example.oracledbdemo.dao.model.Countries;
import com.example.oracledbdemo.dao.model.CountriesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CountriesMapper {
    long countByExample(CountriesExample example);

    int deleteByExample(CountriesExample example);

    int deleteByPrimaryKey(String countryId);

    int insert(Countries row);

    int insertSelective(Countries row);

    List<Countries> selectByExample(CountriesExample example);

    Countries selectByPrimaryKey(String countryId);

    int updateByExampleSelective(@Param("row") Countries row, @Param("example") CountriesExample example);

    int updateByExample(@Param("row") Countries row, @Param("example") CountriesExample example);

    int updateByPrimaryKeySelective(Countries row);

    int updateByPrimaryKey(Countries row);
}