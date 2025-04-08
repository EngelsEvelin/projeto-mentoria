package desafio.java.springboot.dto;

import java.util.DoubleSummaryStatistics;

public class StatisticsResponse {
    private long count;
    private double sum;
    private Double avg;
    private Double max;
    private Double min;

    public StatisticsResponse(DoubleSummaryStatistics stats) {
        this.count = stats.getCount();
        this.sum = stats.getSum();
        this.avg = stats.getCount() > 0 ? stats.getAverage() : null;
        this.max = stats.getCount() > 0 ? stats.getMax() : null;
        this.min = stats.getCount() > 0 ? stats.getMin() : null;
    }

    public long getCount() { return count; }
    public double getSum() { return sum; }
    public Double getAvg() { return avg; }
    public Double getMax() { return max; }
    public Double getMin() { return min; }
}
