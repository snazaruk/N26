package de.nazaruk.services.impl;

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

}
