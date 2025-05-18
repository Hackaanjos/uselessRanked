package com.codecon.hackaton.hackanjos.model.enums;

public enum IntervalFilter {

    DAY,
    WEEK,
    MONTH,
    ALL_TIME;

    public static IntervalFilter fromString(String filter) {
        try {
            return IntervalFilter.valueOf(filter.toUpperCase());
        } catch (Exception e) {
            return IntervalFilter.ALL_TIME;
        }
    }
}
