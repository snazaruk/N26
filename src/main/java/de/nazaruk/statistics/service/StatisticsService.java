package de.nazaruk.statistics.service;

import de.nazaruk.statistics.model.Statistics;
import de.nazaruk.statistics.model.Transaction;

public interface StatisticsService {

    /**
     * Add transaction to the statistics if it was created within last 60 secs
     *
     * @param transaction transaction to add
     * @return true - if transaction was added, false - if not
     */
    boolean add(Transaction transaction);

    /**
     * Return transactions statistics for the last 60 sec
     * @return Statistics
     */
    Statistics getStatisticsForTheLast60Sec();

}
