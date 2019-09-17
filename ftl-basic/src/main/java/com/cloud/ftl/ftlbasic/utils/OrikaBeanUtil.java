package com.cloud.ftl.ftlbasic.utils;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

public class OrikaBeanUtil {

    private static final MapperFacade mapperFacade;

    static {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().useAutoMapping(true).mapNulls(true).build();
        mapperFacade = mapperFactory.getMapperFacade();
    }


    public static <S, D> void map(S from, D to) {
        mapperFacade.map(from, to);
    }

    public static <S, D> D map(S from, Class<D> clazz) {
        return mapperFacade.map(from, clazz);
    }

    public static MapperFacade getMapperFacade() {
        return mapperFacade;
    }

    public static <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        return mapperFacade.mapAsList(source, destinationClass);
    }

}
