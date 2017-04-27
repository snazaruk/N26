package de.nazaruk.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Statistics {

    private double sum;
    private double max;
    private double min;
    private long count;

    public static Statistics fromTransaction(Transaction transaction) {
        return new Statistics(transaction.getAmount(),
                transaction.getAmount(),
                transaction.getAmount(),
                1);
    }

    public static Statistics emptyStatistics() {
        return new Statistics(0, 0, 0, 0);
    }

    public double getAvg() {
        return count == 0 ? 0 : sum / count;
    }

    public Statistics aggregate(Statistics statistics) {
        setCount(getCount() + statistics.getCount());
        setSum(getSum() + statistics.getSum());
        setMax(Math.max(getMax(), statistics.getMax()));
        setMin(Math.min(getMin(), statistics.getMin()));

        return this;
    }
}
