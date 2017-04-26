package de.nazaruk.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

    private double amount;
    private long timestamp;
}
