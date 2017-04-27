package de.nazaruk.statistics.service.impl;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class TimeUtils {

    public boolean isWithinLast60sec(long timestamp) {
        Instant now = Instant.now();
        Instant transactionTimestamp = Instant.ofEpochMilli(timestamp);

        return now.minus(60, ChronoUnit.SECONDS).isBefore(transactionTimestamp) &&
                now.isAfter(transactionTimestamp);
    }

    /**
     * Returns seconds within a minute. Possible values: [0, 59]
     */
    public int getSecondsWithinAMinute(long timestamp) {
        return (int) (timestamp /1000 % 60);
    }

}
