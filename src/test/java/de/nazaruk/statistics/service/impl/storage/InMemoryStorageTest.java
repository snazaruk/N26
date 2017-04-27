package de.nazaruk.statistics.service.impl.storage;

import de.nazaruk.statistics.model.Statistics;
import de.nazaruk.statistics.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryStorageTest {

    @Mock
    private TimeUtils timeUtils;

    @InjectMocks
    private InMemoryStorage inMemoryStorage;

    @Test
    public void should_return_empty_statistics() {
        when(timeUtils.isWithinLast60sec(anyLong())).thenReturn(false);
        assertFalse(inMemoryStorage.addTransaction(mock(Transaction.class)));

        Statistics statistics = inMemoryStorage.getStatisticsForLast60Sec();
        assertEquals(0, Double.compare(statistics.getAvg(), 0));
        assertEquals(0, Double.compare(statistics.getSum(), 0));
        assertEquals(0, Double.compare(statistics.getMax(), 0));
        assertEquals(0, Double.compare(statistics.getMin(), 0));
        assertEquals(statistics.getCount(), 0);
    }

    @Test
    public void should_aggregate_statistics() {
        when(timeUtils.isWithinLast60sec(anyLong())).thenReturn(true);
        assertTrue(inMemoryStorage.addTransaction(new Transaction(100, 0)));
        assertTrue(inMemoryStorage.addTransaction(new Transaction(-40, -1000)));
        assertTrue(inMemoryStorage.addTransaction(new Transaction(0, 1323)));

        Statistics statistics = inMemoryStorage.getStatisticsForLast60Sec();
        assertEquals(0, Double.compare(statistics.getAvg(), 20));
        assertEquals(0, Double.compare(statistics.getSum(), 60));
        assertEquals(0, Double.compare(statistics.getMax(), 100));
        assertEquals(0, Double.compare(statistics.getMin(), -40));
        assertEquals(statistics.getCount(), 3);
    }

    @Test
    public void should_not_use_outdated_statistics() {
        when(timeUtils.isWithinLast60sec(anyLong())).thenReturn(true);
        when(timeUtils.millisToSeconds(10 * 1000)).thenReturn(10l);
        when(timeUtils.millisToSeconds(2 * 1000)).thenReturn(2l);
        when(timeUtils.getSecondsWithinAMinute(10)).thenReturn(10);
        when(timeUtils.getSecondsWithinAMinute(2)).thenReturn(2);

        assertTrue(inMemoryStorage.addTransaction(new Transaction(200.5, 10 * 1000)));
        assertTrue(inMemoryStorage.addTransaction(new Transaction(15.5, 10 * 1000)));
        assertTrue(inMemoryStorage.addTransaction(new Transaction(9, 2 * 1000)));

        Statistics statistics = inMemoryStorage.getStatisticsForLast60Sec();
        assertEquals(0, Double.compare(statistics.getAvg(), 75));
        assertEquals(0, Double.compare(statistics.getSum(), 225));
        assertEquals(0, Double.compare(statistics.getMax(), 200.5));
        assertEquals(0, Double.compare(statistics.getMin(), 9));
        assertEquals(statistics.getCount(), 3);

        // let's say timestamp 2 seconds is too old
        when(timeUtils.isWithinLast60sec(2)).thenReturn(false);

        statistics = inMemoryStorage.getStatisticsForLast60Sec();
        assertEquals(0, Double.compare(statistics.getAvg(), 108));
        assertEquals(0, Double.compare(statistics.getSum(), 216));
        assertEquals(0, Double.compare(statistics.getMax(), 200.5));
        assertEquals(0, Double.compare(statistics.getMin(), 15.5));
        assertEquals(statistics.getCount(), 2);
    }

    @Test
    public void should_return_empty_statistics_when_all_transactions_are_outdated() {
        when(timeUtils.isWithinLast60sec(anyLong())).thenReturn(true);

        assertTrue(inMemoryStorage.addTransaction(new Transaction(200.5, 10 * 1000)));
        assertTrue(inMemoryStorage.addTransaction(new Transaction(9, 2 * 1000)));

        // let's say now every transaction is already too old
        when(timeUtils.isWithinLast60sec(anyLong())).thenReturn(false);
        Statistics statistics = inMemoryStorage.getStatisticsForLast60Sec();
        assertEquals(0, Double.compare(statistics.getAvg(), 0));
        assertEquals(0, Double.compare(statistics.getSum(), 0));
        assertEquals(0, Double.compare(statistics.getMax(), 0));
        assertEquals(0, Double.compare(statistics.getMin(), 0));
        assertEquals(statistics.getCount(), 0);
    }

}
