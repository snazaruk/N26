package de.nazaruk.statistics.service.impl.storage;

import de.nazaruk.statistics.model.Statistics;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatisticsPerSec {

    private long timestampInSeconds;
    private Statistics statistics;

}
