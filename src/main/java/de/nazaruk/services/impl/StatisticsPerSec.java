package de.nazaruk.services.impl;

import de.nazaruk.model.Statistics;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticsPerSec {

    private final long timestamp;
    private Statistics statistics;

    public StatisticsPerSec(long timestamp) {
        this.timestamp = timestamp;
    }
}
