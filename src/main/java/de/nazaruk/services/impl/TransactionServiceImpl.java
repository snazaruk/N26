package de.nazaruk.services.impl;

import de.nazaruk.model.Transaction;
import de.nazaruk.services.StatisticsService;
import de.nazaruk.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TimeUtils timeUtils;

    @Autowired
    private StatisticsService statisticsService;

    @Override
    public boolean add(Transaction transactionRequest) {
        if (timeUtils.isWithinLast60sec(transactionRequest.getTimestamp())) {
            addTransactionToStatisticsAsynchronously(transactionRequest);

            return true;
        }

        return false;
    }

    private void addTransactionToStatisticsAsynchronously(final Transaction transaction) {
        new Thread(() -> {
                statisticsService.addTransaction(transaction);
            }
        ).start();
    }

}
