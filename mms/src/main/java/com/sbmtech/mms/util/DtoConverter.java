package com.sbmtech.mms.util;
@FunctionalInterface
public interface DtoConverter<S, T> {
    T convert(S source);
}