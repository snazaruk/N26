package de.nazaruk.services.impl;

import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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
}
