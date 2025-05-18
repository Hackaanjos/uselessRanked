package com.codecon.hackaton.hackanjos.model.enums;

public enum IntervalFilter {

    DAY,
    WEEK,
    MONTH,
    ALL_TIME;

    public static IntervalFilter fromString(String filter) {
        for (IntervalFilter intervalFilter : IntervalFilter.values()) {
            if (intervalFilter.name().equalsIgnoreCase(filter)) {
                return intervalFilter;
            }
        }
        return IntervalFilter.ALL_TIME;
    }
}
