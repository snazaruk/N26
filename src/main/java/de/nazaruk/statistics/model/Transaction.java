package de.nazaruk.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {

    private double amount;
    private long timestamp;
}
