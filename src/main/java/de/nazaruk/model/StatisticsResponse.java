package de.nazaruk.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticsResponse {

    private double sum;
    private double avg;
    private double max;
    private double min;
    private long count;

}
