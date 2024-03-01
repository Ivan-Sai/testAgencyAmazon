package test.testamazon.services.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import test.testamazon.persistance.entities.report.ReportEntity;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class StatisticsUpdateService {

    private final StatisticsService statisticsService;
    private final ObjectMapper objectMapper;

    public StatisticsUpdateService(StatisticsService statisticsService, ObjectMapper objectMapper) {
        this.statisticsService = statisticsService;
        this.objectMapper = objectMapper;
    }

    public void updateStatistics() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get("test_report.json"));
            ReportEntity report = objectMapper.readValue(jsonData, ReportEntity.class);
            statisticsService.updateReport(report);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Scheduled(fixedRate = 300000) // 5 minutes
    public void scheduledUpdate() {
        System.out.println("Updating statistics"); // щоб було видно виконнання при записі роботи програми
        updateStatistics();
    }
}