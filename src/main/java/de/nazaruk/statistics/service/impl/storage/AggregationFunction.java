package de.nazaruk.statistics.service.impl.storage;

import java.util.function.BinaryOperator;

public class AggregationFunction implements BinaryOperator<StatisticsPerSec> {

    @Override
    public StatisticsPerSec apply(StatisticsPerSec statisticsPerSecond1, StatisticsPerSec newStatisticsPerSec2){
        statisticsPerSecond1.setStatistics(statisticsPerSecond1.getStatistics().aggregate(newStatisticsPerSec2.getStatistics()));
        return statisticsPerSecond1;
    }
}
