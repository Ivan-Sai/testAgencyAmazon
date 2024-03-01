package test.testamazon.api.controllers.personal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.testamazon.persistance.entities.report.SalesAndTrafficByAsin;
import test.testamazon.persistance.entities.report.SalesAndTrafficByDate;
import test.testamazon.services.app.StatisticsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/personal/statistic")
public class StatisticController {

    private final StatisticsService statisticsService;

    @GetMapping("/byDateRange")
    public ResponseEntity<List<SalesAndTrafficByDate>> getStatsByDateRange(
            @RequestParam String startDate,
            @RequestParam(required = false) String endDate) {
        if (endDate == null || endDate.isEmpty()) {
            List<SalesAndTrafficByDate> stats = statisticsService.getStatsByDate(startDate);
            return ResponseEntity.ok(stats);
        }
        List<SalesAndTrafficByDate> stats = statisticsService.getStatsByDateRange(startDate, endDate);
        return ResponseEntity.ok(stats);
    }


    @GetMapping("/byAsin")
    public ResponseEntity<List<SalesAndTrafficByAsin>> getStatsByAsinList(
            @RequestParam List<String> asins) {
        List<SalesAndTrafficByAsin> stats = statisticsService.getStatsByASIN(asins);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/allDate")
    public ResponseEntity<List<SalesAndTrafficByDate>> getTotalStatsByDate() {
        List<SalesAndTrafficByDate> stats = statisticsService.getTotalStatsByDate();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/allAsin")
    public ResponseEntity<List<SalesAndTrafficByAsin>> getTotalStatsByASIN() {
        List<SalesAndTrafficByAsin> stats = statisticsService.getTotalStatsByASIN();
        return ResponseEntity.ok(stats);
    }

}
