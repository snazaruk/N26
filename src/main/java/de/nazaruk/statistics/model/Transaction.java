package de.nazaruk.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Transaction accepts positive and negative values for amount and timestamp.
 */
@Getter
@Setter
@AllArgsConstructor
public class Transaction {

    private double amount;
    private long timestamp;
}
