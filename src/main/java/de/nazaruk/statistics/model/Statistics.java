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
        if (statistics.count == 0) return this;

        setMax(count == 0 ? statistics.getMax() : Math.max(getMax(), statistics.getMax()));
        setMin(count == 0 ? statistics.getMin() : Math.min(getMin(), statistics.getMin()));
        setSum(getSum() + statistics.getSum());
        setCount(getCount() + statistics.getCount());

        return this;
    }
}
