package com.example.oracledbdemo.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Countries {
    private String countryId;

    private String countryName;

    private Short regionId;

}