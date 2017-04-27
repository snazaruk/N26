package de.nazaruk.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Statistics {

    private double sum;
    private double avg;
    private double max;
    private double min;
    private long count;

}
