package de.nazaruk.statistics.service.impl.storage;

import de.nazaruk.statistics.model.Statistics;
import de.nazaruk.statistics.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReferenceArray;

@Component
public class InMemoryStorage {

    public static final AggregationFunction AGGREGATION_FUNCTION = new AggregationFunction();

    @Autowired
    private TimeUtils timeUtils;

    private AtomicReferenceArray<StatisticsPerSec> statisticsPerLast60Sec = new AtomicReferenceArray(new StatisticsPerSec[60]);

    /**
     * Add transaction to the storage if it was created within last 60 secs
     *
     * @param transaction transaction to add
     * @return true - if transaction was added, false - if not
     */
    public boolean addTransaction(Transaction transaction) {
        long transactionTimestampInSeconds = timeUtils.millisToSeconds(transaction.getTimestamp());

        if (!timeUtils.isWithinLast60sec(transactionTimestampInSeconds)) {
            return false;
        }

        int seconds = timeUtils.getSecondsWithinAMinute(transactionTimestampInSeconds);
        StatisticsPerSec currentStatisticsPerSec = statisticsPerLast60Sec.get(seconds);

        StatisticsPerSec newStatisticsPerSec = new StatisticsPerSec(transactionTimestampInSeconds, Statistics.fromTransaction(transaction));
        if (currentStatisticsPerSec != null
            && timeUtils.isWithinLast60sec(currentStatisticsPerSec.getTimestampInSeconds())) {
            statisticsPerLast60Sec.accumulateAndGet(seconds, newStatisticsPerSec, AGGREGATION_FUNCTION);
        } else {
            statisticsPerLast60Sec.set(seconds, newStatisticsPerSec);
        }
        return true;
    }

    public Statistics getStatisticsForLast60Sec() {
        Statistics statistics = Statistics.emptyStatistics();

        // even though we have iterate here the complexity is still O(1) :)
        // it's constant time and memory, because statisticsPerLast60Sec always consists of 60 elements,
        // and statisticsPerSecond.getStatistics() has O(1) as well
        for (int i = 0; i < statisticsPerLast60Sec.length(); i++) {
            StatisticsPerSec statisticsPerSecond = statisticsPerLast60Sec.get(i);
            if (statisticsPerSecond != null
                    && timeUtils.isWithinLast60sec(statisticsPerSecond.getTimestampInSeconds())) {
                statistics.aggregate(statisticsPerSecond.getStatistics());
            }
        }
        return statistics;
    }

}
