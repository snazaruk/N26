package de.nazaruk.services.impl;

import de.nazaruk.model.TransactionRequest;
import de.nazaruk.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TimeUtils timeUtils;

    @Override
    public boolean add(TransactionRequest transactionRequest) {
        if (timeUtils.isWithinLast60sec(transactionRequest.getTimestamp())) {
            //add to statistics

            return true;
        }

        return false;
    }

}
