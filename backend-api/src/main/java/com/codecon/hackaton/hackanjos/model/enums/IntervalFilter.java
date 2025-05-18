package com.codecon.hackaton.hackanjos.model.enums;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public static LocalDateTime getLocalDateTimeByIntervalFilter(IntervalFilter intervalFilter) {
        return switch (intervalFilter) {
            case DAY -> LocalDate.now().atStartOfDay();
            case WEEK -> LocalDate.now().minusDays(7).atStartOfDay();
            case MONTH -> LocalDate.now().minusMonths(1).atStartOfDay();
            case ALL_TIME -> LocalDate.now().minusYears(100).atStartOfDay();
        };
    }
}
