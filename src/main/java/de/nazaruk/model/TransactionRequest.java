package de.nazaruk.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

    private double amount;
    private long timestamp;
}
