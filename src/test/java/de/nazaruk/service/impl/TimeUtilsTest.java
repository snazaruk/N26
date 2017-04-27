package de.nazaruk.statistics.service.impl;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import static java.time.ZoneOffset.UTC;

public class TimeUtilsTest {

    private TimeUtils timeUtils = new TimeUtils();

    @Test
    public void should_return_true_if_within_60_secs() {
        assertTrue(timeUtils.isWithinLast60sec(Instant.now().minus(0, ChronoUnit.SECONDS).toEpochMilli()));
        assertTrue(timeUtils.isWithinLast60sec(Instant.now().minus(1, ChronoUnit.SECONDS).toEpochMilli()));
        assertTrue(timeUtils.isWithinLast60sec(Instant.now().minus(59, ChronoUnit.SECONDS).toEpochMilli()));
    }

    @Test
    public void should_return_false_if_not_within_60_secs() {
        assertFalse(timeUtils.isWithinLast60sec(Instant.now().plus(2, ChronoUnit.SECONDS).toEpochMilli()));
        assertFalse(timeUtils.isWithinLast60sec(Instant.now().minus(60, ChronoUnit.SECONDS).toEpochMilli()));
        assertFalse(timeUtils.isWithinLast60sec(Instant.now().minus(61, ChronoUnit.SECONDS).toEpochMilli()));
    }

    @Test
    public void to_seconds() {
        assertEquals(34, timeUtils.getSecondsWithinAMinute(LocalDateTime.of(2017, 4, 26, 23, 47, 34).toInstant(UTC).toEpochMilli()));
        assertEquals(0, timeUtils.getSecondsWithinAMinute(LocalDateTime.of(2000, 1, 6, 3, 43, 0).toInstant(UTC).toEpochMilli()));
        assertEquals(59, timeUtils.getSecondsWithinAMinute(LocalDateTime.of(2022, 4, 3, 23, 59, 59).toInstant(UTC).toEpochMilli()));
        assertEquals(59, timeUtils.getSecondsWithinAMinute(LocalDateTime.of(2022, 4, 3, 23, 59, 59).toInstant(UTC)
                .plus(3, ChronoUnit.MILLIS).toEpochMilli()));
    }


}
