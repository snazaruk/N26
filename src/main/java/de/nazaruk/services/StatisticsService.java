package de.nazaruk.services;

import de.nazaruk.model.Statistics;
import de.nazaruk.model.Transaction;

public interface StatisticsService {

    Statistics getStatisticsForTheLast60Sec();

    void addTransaction(Transaction transaction);
}
