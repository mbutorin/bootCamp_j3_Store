package com.colvir.j3.store.dto;

import java.util.LinkedHashMap;
import java.util.Map;

/*
* How to fetch a one-to-many DTO projection with JPA and Hibernate - Vlad Mihalcea
* https://vladmihalcea.com/one-to-many-dto-projection-hibernate/
* */

public class OneToManyUtilities {

    public static Map<String, Integer> aliasToIndexMap(
            String[] aliases) {

        Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();

        for (int i = 0; i < aliases.length; i++) {
            aliasToIndexMap.put(aliases[i], i);
        }

        return aliasToIndexMap;
    }
}
