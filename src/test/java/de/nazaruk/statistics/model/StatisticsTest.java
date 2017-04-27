package de.nazaruk.statistics.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticsTest {

    @Test
    public void fromTransaction() {
        Statistics statistics = Statistics.fromTransaction(new Transaction(3, 0));
        assertEquals(0, Double.compare(statistics.getAvg(), 3));
        assertEquals(0, Double.compare(statistics.getSum(), 3));
        assertEquals(0, Double.compare(statistics.getMax(), 3));
        assertEquals(0, Double.compare(statistics.getMin(), 3));
        assertEquals(statistics.getCount(), 1);
    }

    @Test
    public void aggregate_with_empty_statistics() {
        Statistics fromTransaction = Statistics.fromTransaction(new Transaction(3, 0));
        fromTransaction.aggregate(Statistics.emptyStatistics());
        assertEquals(0, Double.compare(fromTransaction.getAvg(), 3));
        assertEquals(0, Double.compare(fromTransaction.getSum(), 3));
        assertEquals(0, Double.compare(fromTransaction.getMax(), 3));
        assertEquals(0, Double.compare(fromTransaction.getMin(), 3));
        assertEquals(fromTransaction.getCount(), 1);
    }

    @Test
    public void aggregate_empty_statistics_with_others() {
        Statistics statistics = Statistics.emptyStatistics();
        statistics.aggregate(Statistics.fromTransaction(new Transaction(100, 0)));
        statistics.aggregate(Statistics.fromTransaction(new Transaction(-10, 0)));
        statistics.aggregate(Statistics.fromTransaction(new Transaction(30, 0)));

        assertEquals(0, Double.compare(statistics.getAvg(), 40));
        assertEquals(0, Double.compare(statistics.getSum(), 120));
        assertEquals(0, Double.compare(statistics.getMax(), 100));
        assertEquals(0, Double.compare(statistics.getMin(), -10));
        assertEquals(statistics.getCount(), 3);
    }

    @Test
    public void aggregate_empty_statistics_with_empty() {
        Statistics statistics = Statistics.emptyStatistics().aggregate(Statistics.emptyStatistics());
        assertEquals(0, Double.compare(statistics.getAvg(), 0));
        assertEquals(0, Double.compare(statistics.getSum(), 0));
        assertEquals(0, Double.compare(statistics.getMax(), 0));
        assertEquals(0, Double.compare(statistics.getMin(), 0));
        assertEquals(statistics.getCount(), 0);
    }
}
