package de.nazaruk.statistics.service.impl;

import de.nazaruk.statistics.model.Statistics;
import de.nazaruk.statistics.model.Transaction;
import de.nazaruk.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private TimeUtils timeUtils;

    @Autowired
    private InMemoryStorage statisticsStorage;


    @Override
    public Statistics getStatisticsForTheLast60Sec() {
        return statisticsStorage.getStatisticsForLast60Sec();
    }

    @Override
    public boolean add(Transaction transaction) {
        if (timeUtils.isWithinLast60sec(transaction.getTimestamp())) {
            statisticsStorage.addTransaction(transaction);
            return true;
        }
        return false;

    }
}
