package de.nazaruk.services.impl;

import de.nazaruk.model.Statistics;
import de.nazaruk.model.Transaction;
import de.nazaruk.services.StatisticsService;
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
