package de.nazaruk.statistics.service.impl;

import de.nazaruk.statistics.model.Statistics;
import de.nazaruk.statistics.model.Transaction;
import de.nazaruk.statistics.service.StatisticsService;
import de.nazaruk.statistics.service.impl.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private InMemoryStorage statisticsStorage;

    @Override
    public boolean add(Transaction transaction) {
        return statisticsStorage.addTransaction(transaction);
    }

    @Override
    public Statistics getStatisticsForTheLast60Sec() {
        return statisticsStorage.getStatisticsForLast60Sec();
    }
}
