package test.testamazon.services.app;

import test.testamazon.persistance.entities.report.ReportEntity;
import test.testamazon.persistance.entities.report.SalesAndTrafficByAsin;
import test.testamazon.persistance.entities.report.SalesAndTrafficByDate;

import java.util.List;

public interface StatisticsService {
    List<SalesAndTrafficByDate> getStatsByDate(String date);

    List<SalesAndTrafficByDate> getStatsByDateRange(String startDate, String endDate);

    List<SalesAndTrafficByAsin> getStatsByASIN(List<String> asins);

    List<SalesAndTrafficByDate> getTotalStatsByDate();

    List<SalesAndTrafficByAsin> getTotalStatsByASIN();

    void updateReport(ReportEntity report);
}
