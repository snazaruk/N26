package de.nazaruk.statistics.service.impl.storage;

import de.nazaruk.statistics.model.Statistics;
import de.nazaruk.statistics.model.Transaction;
import de.nazaruk.statistics.service.impl.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReferenceArray;

@Component
public class InMemoryStorage {

    @Autowired
    private TimeUtils timeUtils;

    private AtomicReferenceArray<StatisticsPerSec> statisticsPerLast60Sec = new AtomicReferenceArray(new StatisticsPerSec[60]);

    public void addTransaction(Transaction transaction) {
        long transactionTimestampInSeconds = timeUtils.millisToSeconds(transaction.getTimestamp());

        int seconds = timeUtils.getSecondsWithinAMinute(transactionTimestampInSeconds);
        StatisticsPerSec currentStatisticsPerSec = statisticsPerLast60Sec.get(seconds);

        StatisticsPerSec newStatisticsPerSec = new StatisticsPerSec(transactionTimestampInSeconds, Statistics.fromTransaction(transaction));
        if (timeUtils.isWithinLast60sec(currentStatisticsPerSec.getTimestampInSeconds())) {
            statisticsPerLast60Sec.accumulateAndGet(seconds, newStatisticsPerSec, new AggregationFunction());
        } else {
            statisticsPerLast60Sec.set(seconds, newStatisticsPerSec);
        }
    }

    public Statistics getStatisticsForLast60Sec() {
        Statistics statistics = Statistics.emptyStatistics();

        // even though we have iterate here the complexity is still O(1) :)
        // it's constant time, because statisticsPerLast60Sec always consists of 60 elements.
        // end statisticsPerSecond.getStatistics() has O(1) as well
        for (int i = 0; i < statisticsPerLast60Sec.length(); i++) {
            StatisticsPerSec statisticsPerSecond = statisticsPerLast60Sec.get(i);
            if (timeUtils.isWithinLast60sec(statisticsPerSecond.getTimestampInSeconds())) {
                statistics.aggregate(statisticsPerSecond.getStatistics());
            }
        }
        return statistics;
    }

}
