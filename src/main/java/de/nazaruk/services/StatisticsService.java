package de.nazaruk.services;

import de.nazaruk.model.Statistics;
import de.nazaruk.model.Transaction;

public interface StatisticsService {

    /**
     * Return transactions statistics for the last 60 sec
     * @return Statistics
     */
    Statistics getStatisticsForTheLast60Sec();

    /**
     * Adds transaction to the statistics
     * @param transaction Transaction which will be added
     */
    void addTransaction(Transaction transaction);
}
