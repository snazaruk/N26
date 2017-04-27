package de.nazaruk.services.impl;

import de.nazaruk.model.Statistics;
import de.nazaruk.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InMemoryStorage {

    @Autowired
    private TimeUtils timeUtils;

    private CopyOnWriteArrayList<StatisticsPerSec> statisticsPerLast60Sec = new CopyOnWriteArrayList(new StatisticsPerSec[60]);

    public void addTransaction(Transaction transaction) {
        if (!timeUtils.isWithinLast60sec(transaction.getTimestamp())) {
            return;
        }

        int secondsWithinAMinute = timeUtils.getSecondsWithinAMinute(transaction.getTimestamp());
        StatisticsPerSec existedStatisticsPerSec = statisticsPerLast60Sec.get(secondsWithinAMinute);

        if (!timeUtils.isWithinLast60sec(existedStatisticsPerSec.getTimestamp())) {
            statisticsPerLast60Sec.remove(secondsWithinAMinute);
            //TODO maybe round it...
            StatisticsPerSec statisticsPerSec = new StatisticsPerSec(transaction.getTimestamp());

            statisticsPerSec.setStatistics(new Statistics(transaction.getAmount(),
                    transaction.getAmount(),
                    transaction.getAmount(),
                    transaction.getAmount(),
                    1));

            statisticsPerLast60Sec.add(secondsWithinAMinute, statisticsPerSec);
        } else {
            //TODO
        }
    }

    public Statistics getStatisticsForLast60Sec() {

        return null;
    }
}
