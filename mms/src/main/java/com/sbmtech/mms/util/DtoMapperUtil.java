package com.sbmtech.mms.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DtoMapperUtil {

    public static <S, T> T map(S source, DtoConverter<S, T> converter) {
        return converter.convert(source);
    }

    public static <S, T> List<T> mapList(Collection<S> sourceList, DtoConverter<S, T> converter) {
        return sourceList.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}