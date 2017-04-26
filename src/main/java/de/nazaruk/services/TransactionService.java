package de.nazaruk.services;

import de.nazaruk.model.Transaction;

public interface TransactionService {

    /**
     * Add transaction to the memory if it was created within last 60 secs
     *
     * @param transactionRequest transaction to add
     * @return true - if transaction was added, false - if not
     */
    boolean add(Transaction transactionRequest);
}
