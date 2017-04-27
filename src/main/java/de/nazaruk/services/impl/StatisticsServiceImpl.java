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
    private StatisticsInMemoryStorage statisticsStorage;


    @Override
    public Statistics getStatisticsForTheLast60Sec() {
        return null;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        statisticsStorage.addTransaction(transaction);
    }
}
