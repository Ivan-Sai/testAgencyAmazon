package test.testamazon.persistance.entities.report;

import lombok.Data;

@Data
public class MonetaryValue {
    private double amount;
    private String currencyCode;
}