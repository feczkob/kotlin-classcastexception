package com.example.controller.converter;

import org.mapstruct.Condition;
import org.openapitools.jackson.nullable.JsonNullable;

public interface BaseMapper {

    default Integer mapInt(JsonNullable<Integer> from) {
        return from != null ? from.get() : null;
    }

    default String mapString(JsonNullable<String> from) {
        return from != null ? from.get() : null;
    }

    // * https://mapstruct.org/news/2021-07-18-mapstruct-1_5_0_Beta1-is-out/
    @Condition
    default boolean isPresentString(JsonNullable<String> value) {
        return value != null;
    }

    @Condition
    default boolean isPresentInteger(JsonNullable<Integer> value) {
        return value != null;
    }
}
