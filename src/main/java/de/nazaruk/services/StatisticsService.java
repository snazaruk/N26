package de.nazaruk.services;

import de.nazaruk.model.Statistics;
import de.nazaruk.model.Transaction;

public interface StatisticsService {

    /**
     * Add transaction to the statistics if it was created within last 60 secs
     *
     * @param transactionRequest transaction to add
     * @return true - if transaction was added, false - if not
     */
    boolean add(Transaction transactionRequest);

    /**
     * Return transactions statistics for the last 60 sec
     * @return Statistics
     */
    Statistics getStatisticsForTheLast60Sec();

}
