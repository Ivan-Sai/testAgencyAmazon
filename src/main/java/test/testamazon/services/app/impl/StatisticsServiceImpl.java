package test.testamazon.services.app.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import test.testamazon.exceptions.BadRequestException;
import test.testamazon.persistance.entities.report.ReportEntity;
import test.testamazon.persistance.entities.report.SalesAndTrafficByAsin;
import test.testamazon.persistance.entities.report.SalesAndTrafficByDate;
import test.testamazon.persistance.repositories.ReportRepository;
import test.testamazon.persistance.repositories.SalesAndTrafficByDateRepository;
import test.testamazon.services.app.StatisticsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final ReportRepository reportRepository;
    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;

    public ReportEntity getReport() {
        return reportRepository.findAll().stream().findFirst().orElse(null);
    }

    @Override
    @Cacheable("statsByDate")
    public List<SalesAndTrafficByDate> getStatsByDate(String date) {
        if (date == null || date.isEmpty()) {
            throw new BadRequestException("Date is required");
        }
        ReportEntity report = getReport();
        if (report != null) {
            return report.getSalesAndTrafficByDate().stream()
                    .filter(salesAndTrafficByDate -> salesAndTrafficByDate.getDate().equals(date))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    @Override
    @Cacheable("statsByDateRange")
    public List<SalesAndTrafficByDate> getStatsByDateRange(String startDate, String endDate) {
        if (startDate == null || startDate.isEmpty() || endDate == null || endDate.isEmpty()) {
            throw new BadRequestException("Date is required");
        }
        ReportEntity report = getReport();
        if (report != null) {
            return report.getSalesAndTrafficByDate().stream()
                    .filter(salesAndTrafficByDate -> salesAndTrafficByDate.getDate().compareTo(startDate) >= 0
                            && salesAndTrafficByDate.getDate().compareTo(endDate) <= 0)
                    .collect(Collectors.toList());
        }

        return List.of();
    }

    @Override
    @Cacheable("statsByASIN")
    public List<SalesAndTrafficByAsin> getStatsByASIN(List<String> asins) {
        if (asins == null || asins.isEmpty()) {
            throw new BadRequestException("Asins is required");
        }
        ReportEntity report = getReport();
        if (report != null) {
            return report.getSalesAndTrafficByAsin().stream()
                    .filter(salesAndTrafficByAsin -> asins.contains(salesAndTrafficByAsin.getParentAsin()))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    @Override
    @Cacheable("statsByAllDate")
    public List<SalesAndTrafficByDate> getTotalStatsByDate() {
        ReportEntity report = getReport();
        if (report != null) {
            return report.getSalesAndTrafficByDate();
        }
        return List.of();
    }

    @Override
    @Cacheable("statsByAllAsin")
    public List<SalesAndTrafficByAsin> getTotalStatsByASIN() {
        ReportEntity report = getReport();
        if (report != null) {
            return report.getSalesAndTrafficByAsin();
        }
        return List.of();
    }

    @Override
    @CacheEvict(cacheNames = {"statsByDate", "statsByDateRange", "statsByASIN", "statsByAllDate", "statsByAllAsin"}, allEntries = true)
    public void updateReport(ReportEntity report) {
        ReportEntity oldReport = getReport();
        if (oldReport != null) {
            oldReport.setReportSpecification(report.getReportSpecification());
            oldReport.setSalesAndTrafficByDate(report.getSalesAndTrafficByDate());
            oldReport.setSalesAndTrafficByAsin(report.getSalesAndTrafficByAsin());
            reportRepository.save(oldReport);
        } else {
            reportRepository.save(report);
        }
    }


}
