package test.testamazon.persistance.entities.report;

import lombok.Data;

@Data
public class SalesAndTrafficByDate {
    private String date;
    private SalesData salesByDate;
    private TrafficData trafficByDate;
}
