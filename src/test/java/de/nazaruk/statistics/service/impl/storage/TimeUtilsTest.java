package de.nazaruk.statistics.service.impl.storage;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static java.time.ZoneOffset.UTC;
import static org.junit.Assert.*;

public class TimeUtilsTest {

    private TimeUtils timeUtils = new TimeUtils();

    @Test
    public void isWithinLast60sec() {
        assertTrue(timeUtils.isWithinLast60sec(Instant.now().minus(0, ChronoUnit.SECONDS).getEpochSecond()));
        assertTrue(timeUtils.isWithinLast60sec(Instant.now().minus(1, ChronoUnit.SECONDS).getEpochSecond()));
        assertTrue(timeUtils.isWithinLast60sec(Instant.now().minus(59, ChronoUnit.SECONDS).getEpochSecond()));

        assertFalse(timeUtils.isWithinLast60sec(Instant.now().plus(2, ChronoUnit.SECONDS).getEpochSecond()));
        assertFalse(timeUtils.isWithinLast60sec(Instant.now().minus(60, ChronoUnit.SECONDS).getEpochSecond()));
        assertFalse(timeUtils.isWithinLast60sec(Instant.now().minus(61, ChronoUnit.SECONDS).getEpochSecond()));
    }

    @Test
    public void getSecondsWithinAMinute() {
        assertEquals(34, timeUtils.getSecondsWithinAMinute(LocalDateTime.of(2017, 4, 26, 23, 47, 34).toInstant(UTC).getEpochSecond()));
        assertEquals(0, timeUtils.getSecondsWithinAMinute(LocalDateTime.of(2000, 1, 6, 3, 43, 0).toInstant(UTC).getEpochSecond()));
        assertEquals(59, timeUtils.getSecondsWithinAMinute(LocalDateTime.of(2022, 4, 3, 23, 59, 59).toInstant(UTC).getEpochSecond()));
        assertEquals(59, timeUtils.getSecondsWithinAMinute(LocalDateTime.of(2022, 4, 3, 23, 59, 59).toInstant(UTC)
                .plus(3, ChronoUnit.MILLIS).getEpochSecond()));
    }

    @Test
    public void millisToSeconds() {
        assertEquals(0, timeUtils.millisToSeconds(0));
        assertEquals(0, timeUtils.millisToSeconds(500));
        assertEquals(0, timeUtils.millisToSeconds(999));
        assertEquals(2, timeUtils.millisToSeconds(2599));
        assertEquals(55, timeUtils.millisToSeconds(1000 * 55 + 43));
    }

}
