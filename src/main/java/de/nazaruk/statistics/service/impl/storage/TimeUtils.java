package de.nazaruk.statistics.service.impl.storage;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class TimeUtils {

    public boolean isWithinLast60sec(long timestampInSeconds) {
        Instant now = Instant.now();
        Instant transactionInstant = Instant.ofEpochSecond(timestampInSeconds);

        return now.minus(60, ChronoUnit.SECONDS).isBefore(transactionInstant) &&
                now.isAfter(transactionInstant);
    }

    /**
     * Returns seconds within a minute. Possible values: [0, 59]
     * @param timestampInSeconds timestamp in seconds
     * @return seconds
     */
    public int getSecondsWithinAMinute(long timestampInSeconds) {
        return (int) (timestampInSeconds % 60);
    }

    /**
     * Rounds milliseconds to seconds. Example:
     *  - 555 milliseconds -> 0 seconds
     *  - 3212 milliseconds -> 3 seconds
     * @param timestampInMillis timestamp in milliseconds
     * @return seconds
     */
    public long millisToSeconds(long timestampInMillis) {
        return timestampInMillis / 1000;
    }
}
