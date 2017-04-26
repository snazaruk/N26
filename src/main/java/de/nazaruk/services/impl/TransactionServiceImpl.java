package de.nazaruk.services.impl;

import de.nazaruk.services.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public boolean create() {
        return false;
    }
}
