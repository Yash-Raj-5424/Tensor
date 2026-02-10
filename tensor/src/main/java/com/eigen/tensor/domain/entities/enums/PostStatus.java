package com.eigen.tensor.domain.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PostStatus {
    DRAFT, PUBLISHED;

    @JsonCreator
    public static PostStatus fromValue(String value){
        return PostStatus.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue(){
        return this.name().toLowerCase();
    }
}
